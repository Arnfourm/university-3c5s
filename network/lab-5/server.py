import ssl
from http.server import HTTPServer, SimpleHTTPRequestHandler

context = ssl.SSLContext(ssl.PROTOCOL_TLS_SERVER)
context.load_cert_chain(certfile="server.pem", keyfile="key.pem")

httpd = HTTPServer(("0.0.0.0", 4443), SimpleHTTPRequestHandler)
httpd.socket = context.wrap_socket(httpd.socket, server_side=True)
httpd.serve_forever()