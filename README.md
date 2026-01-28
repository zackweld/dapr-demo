# dapr-demo
## Diagrid SE Challenge

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

- Contoller path - http://localhost:9001

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