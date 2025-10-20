from fastapi import FastAPI, HTTPException
from fastapi.responses import FileResponse, StreamingResponse
import os
import requests

application = FastAPI()

additional_servers = [
    {"protocol": "http", "host": "localhost", "port": 8111}
]

files_dir = f"{os.getcwd()}/firstServer/files/"
server_count = "first"

@application.get("/api/files")
def getAllFiles():
    files = []
    for filename in os.listdir(files_dir):
        filepath = f"{files_dir}/{filename}"
        files.append({
            "name": filename,
            "path": filepath,
            "server": server_count
        })

    response = requests.get(
        f"{additional_servers[0]['protocol']}://{additional_servers[0]['host']}:{additional_servers[0]['port']}/api/files"
    )
        
    if response.status_code == 200:
        files.extend(response.json()["files"])

    return {"files": files}

@application.get("/api/files/{item_name}")
def getFileByName(item_name: str):
    file_path = os.path.join(files_dir, item_name)

    if os.path.isfile(file_path):
        return FileResponse(
            path = file_path,
            filename = item_name,
            media_type="application/octet-stream"
        )

    response = requests.get(
        f"{additional_servers[0]['protocol']}://{additional_servers[0]['host']}:{additional_servers[0]['port']}/api/files/{item_name}",
        stream=True
    )

    if response.status_code == 200:
        return StreamingResponse(
            response.iter_content(chunk_size=8192),
            media_type=response.headers.get("content-type"),
            headers={"Content-Disposition": f"attachment; filename={item_name}"}
        )

    raise HTTPException(status_code=404, detail="File not found")