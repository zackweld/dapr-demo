# dapr-demo
Diagrid SA Challenge

 ![Alt Text](https://private-user-images.githubusercontent.com/23583778/541840095-a07cdec5-a87f-4ef2-a7f5-e7e30268d3e3.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Njk2MzExNjIsIm5iZiI6MTc2OTYzMDg2MiwicGF0aCI6Ii8yMzU4Mzc3OC81NDE4NDAwOTUtYTA3Y2RlYzUtYTg3Zi00ZWYyLWE3ZjUtZTdlMzAyNjhkM2UzLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNjAxMjglMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjYwMTI4VDIwMDc0MlomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTlkZTUzN2YyMTQzNzczOGE1Y2U3Mzk4MzgwNjAxMDQ0NDczY2MxZTI3MWJkOTkzY2FlZTJlYzM4ODMwOWFiOTQmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.v2UqlIwmN9nWn8uBQtuEXG80MPukJURaHqvdVeI_4ZE)

# Build

- cd rest-client/
- mvn clean install
- cd ..

# Run

- Dapr
    - dapr run -f .
- Diagrid
    - diagrid dev run --app-id rest-client "dapr run -f ."

# Http Controller 

- Contoller path - http://localhost:{HTTP PORT}/

    - Header -> dapr-app-id = rest-client

- Create Ticket
    - POST
    - Path extension - /ticket

- Retrieve Ticket
    - GET
    - Path extension - /ticket

- Delete Ticket
    - DELETE
    - Path extension - /ticket

# Workflow

- Initiate Workflow (Same controller as above)
    - POST
    - Path extension - /initiateWorkflow

## Workflow Process - Ticket Approval
- Create Ticket
- Notify
- Approve Ticket
- Notify

# Catalyst Info

- DAPR_HTTP_ENDPOINT - https://http-prj545607.cloud.r1.diagrid.io:443

- DAPR_GRPC_ENDPOINT - https://grpc-prj545607.cloud.r1.diagrid.io:443

- DAPR_API_TOKEN - diagrid://v1/00f384e4-06df-441f-87dd-fc0308a75308/545607/dapr-demo/rest-client/9f47310e-a2a9-439a-991e-659660cdecd8

- Spiffe ID - spiffe://vxjiifii.diagrid-aws-eu-west.00f384e4-06df-441f-87dd-fc0308a75308.public.diagrid.io/ns/prj-545607/my-dapr-demo-app
