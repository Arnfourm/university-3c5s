from fastapi import FastAPI, HTTPException
from fastapi.responses import FileResponse
import os

application = FastAPI()

files_dir = f"{os.getcwd()}/secondServer/files/"
server_count = "second"

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
    
    raise HTTPException(status_code=404, detail="File not found")