README 


Example Requests for VSC

### Update Requests
#### Update Job
PUT http://localhost:8080/api/jobs/52
Headers:
Content-Type: application/json

{
  "id": 52,
  "title": "UpdatedTitle",
  "description": "UpdatedDescription",
  "company_id": "UpdatedCompanyID",
  "anzahl": "1",
  "status": "UpdatedStatus",
  "pensum": "UpdatedPensum"
}

#### Update Extra
PUT http://localhost:8080/api/extras/52
Headers:
Content-Type: application/json

{
  "id": 52,
  "remote": false,
  "flexibel": false,
  "sign_up": false,
  "devices": false,
  "extrapay": false,
  "sonstiges": "UpdatedSonstiges"
}

#### Update Company
PUT http://localhost:8080/api/company/52
Headers:
Content-Type: application/json

{
  "id": 52,
  "name": "UpdatedCompanyName",
  "logo_url": "UpdatedLogoURL",
  "address": "UpdatedAddress",
  "plz": "132",
  "ort": "UpdatedOrt",
  "website": "UpdatedWebsite"
}

### Create Requests
#### Create Job
POST http://localhost:8080/api/jobs
Headers:
Content-Type: application/json

{
  "title": "NewJob",
  "description": "NewJobDescription",
  "company_id": 15,
  "anzahl": "500",
  "status": "NewStatus",
  "pensum": "NewPensum"
}

#### Create Extra
POST http://localhost:8080/api/extras
Headers:
Content-Type: application/json

{
  "remote": true,
  "flexibel": false,
  "sign_up": true,
  "devices": false,
  "extrapay": true,
  "sonstiges": "NewSonstiges"
}

#### Create Company
POST http://localhost:8080/api/company
Headers:
Content-Type: application/json

{
  "name": "NewCompanyName",
  "logo_url": "NewLogoURL",
  "address": "NewAddress",
  "plz": "NewPLZ",
  "ort": "NewOrt",
  "website": "NewWebsite"
}

### Fetch Requests
#### Fetch Job by ID
GET http://localhost:8080/api/jobs/52

#### Fetch Extra by ID
GET http://localhost:8080/api/extras/52

#### Fetch Company by ID
GET http://localhost:8080/api/company/52

#### Fetch All Jobs
GET http://localhost:8080/api/jobs

#### Fetch All Extras
GET http://localhost:8080/api/extras

#### Fetch All Companies
GET http://localhost:8080/api/company

### Delete Requests
#### Delete Job by ID
DELETE http://localhost:8080/api/jobs/54

#### Delete Extra by ID
DELETE http://localhost:8080/api/extras/52

#### Delete Company by ID
DELETE http://localhost:8080/api/company/15
###
GET http://localhost:8080/api/company/stat/total