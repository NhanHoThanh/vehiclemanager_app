# Drivers API

## Endpoints

### GET /api/drivers

Get ALL drivers

**URL**: `http://localhost:8080/api/drivers`

**Method**: `GET`

**Headers**:

-   `Content-Type: application/json`

Example of return data:

```json
{
    "status": "success",
    "message": "Get all drivers successfully",
    "data": [
        {
            "id": "20240410121021",
            "name": "Ho Thanh Nhan",
            "address": "124 Vo Thi Sau, Quan 10, TP.HCM",
            "phone_number": "0976481171",
            "license": "C2",
            "cccd": "54865484654",
            "routeId": "TP.HCM - BaRiaVungTau",
            "status": "available",
            "vehicleId": "1",
            "vehicleType": "Truck",
            "email": "hothanhnhanhot@gmail.com",
            "createdAt": null
        },
        {
            "id": "20240410121712",
            "name": "Nguyen Van Troi",
            "address": "456 Nguyen Van Cu, Quan 5, TP.HCM",
            "phone_number": "0909123456",
            "license": "B1",
            "cccd": "1234567890",
            "routeId": "TP.HCM - Long An",
            "status": "available",
            "vehicleId": "2",
            "vehicleType": "Couch",
            "email": "nguyenvana@example.com",
            "createdAt": null
        }
    ]
}
```

---

### GET /api/drivers/{id}

Get a driver by ID.

**URL**: `http://localhost:8080/api/drivers/{id}`

**Method**: `GET`

**Headers**:

-   `Content-Type: application/json`

Example of return data:

```json
{
    "status": "success",
    "message": "Get driver by id successfully",
    "data": {
        "id": "20240410230323",
        "name": "Tran Thi C",
        "address": "122 Le Van Sy, Quan Tan Binh, TP.HCM",
        "phone_number": "05418234567",
        "license": "A2",
        "cccd": "54865484654",
        "routeId": "TP.HCM - BaRiaVungTau",
        "status": "available",
        "vehicleId": "1",
        "vehicleType": "Truck",
        "email": "hothanhnhanhot@gmail.com",
        "createdAt": "23-03-23 10-04-2024"
    }
}
```

---

### POST /api/drivers

Create a new driver.

**URL**: `http://localhost:8080/api/drivers`

**Method**: `POST`

**Headers**:

-   `Content-Type: application/json`

**Body**: _Must_ include all these field:

```json
{
    "address": "124 Vo Thi Sau, Quan 10, TP.HCM",
    "cccd": "54865484654",
    "license": "C2",
    "name": "Ho Thanh Nhan",
    "phone_number": "0976481171",
    "email": "hothanhnhanhot@gmail.com"
}
```

---

### PUT /api/drivers{id}

Update a new driver with id.

**URL**: `http://localhost:8080/api/drivers/{id}`

**Method**: `PUT`

**Headers**:

-   `Content-Type: application/json`

**Body**: _Should_ include _one/any_ these fields:

```json
{
    "address": "124 Vo Thi Sau, Quan 10, TP.HCM",
    "cccd": "54865484654",
    "license": "C2",
    "name": "Ho Thanh Nhan",
    "phone_number": "0976481171",
    "email": "hothanhnhanhot@gmail.com"
}
```

**Return data**:

```json
{
    "status": "success",
    "message": "Update driver successfully",
    "data": {
        "id": "20240410230323",
        "name": "Tran Thi C",
        "address": "122 Le Van Sy, Quan Tan Binh, TP.HCM",
        "phone_number": "05418234567",
        "license": "A2",
        "cccd": "54865484654",
        "routeId": "TP.HCM - BaRiaVungTau",
        "status": "available",
        "vehicleId": "1",
        "vehicleType": "Truck",
        "email": "hothanhnhanhot@gmail.com",
        "createdAt": "23-03-23 10-04-2024"
    }
}
```

---

### PUT /api/drivers/{id}/vehicle

Update vehicle for a driver with id.

**URL**: `http://localhost:8080/api/drivers/{id}/vehicle`

**Method**: `PUT`

**Headers**:

-   `Content-Type: application/json`

**Body**: _Must_ include these fields:

```json
{
    "vehicleId": "1234",
    "vehicleType": "Truck"
}
```

**Interaction**

This API endpoint also interacts with the Vehicle Service API to add the driver to the vehicle. The Vehicle Service API is called with the following details:

URL: `URL ben phia Tam cung cap`

Method: `???`

Headers: `Content-Type: application/json`

Body: _Must_ include these fields:

```json
{
    "driverId": "1234"
}
```

**Return data**:

```json
{
    "status": "success",
    "message": "Update vehicle successfully",
    "data": {
        "id": "20240410230323",
        "name": "Tran Thi C",
        "address": "122 Le Van Sy, Quan Tan Binh, TP.HCM",
        "phone_number": "05418234567",
        "license": "A2",
        "cccd": "54865484654",
        "routeId": "TP.HCM - BaRiaVungTau",
        "status": "available",
        "vehicleId": "1",
        "vehicleType": "Truck",
        "email": "hothanhnhanhot@gmail.com",
        "createdAt": "23-03-23 10-04-2024"
    }
}
```

---

### PUT /api/drivers/{id}/route

Update route for a driver with id.

**URL**: `http://localhost:8080/api/drivers/{id}/route`

**Method**: `PUT`

**Headers**:

-   `Content-Type: application/json`

**Body**: _Must_ include these fields:

```json
{
    "routeId": "1234"
}
```

**Interaction**

This API endpoint also interacts with the Route Service API to add the driver's vehicle to the route. The Route Service API is called with the following details:

URL: `URL ben phia Thach cung cap`

Method: `???`

Headers: `Content-Type: application/json`

Body: _Must_ include these fields:

```json
{
    "driverId": "1234"
}
```

**Return data**:

```json
{
    "status": "success",
    "message": "Update route successfully",
    "data": {
        "id": "20240410230323",
        "name": "Tran Thi C",
        "address": "122 Le Van Sy, Quan Tan Binh, TP.HCM",
        "phone_number": "05418234567",
        "license": "A2",
        "cccd": "54865484654",
        "routeId": "TP.HCM - BaRiaVungTau",
        "status": "available",
        "vehicleId": "1",
        "vehicleType": "Truck",
        "email": "hothanhnhanhot@gmail.com",
        "createdAt": "23-03-23 10-04-2024"
    }
}
```

---

### DELETE /api/drivers/{id}

Delete a driver by ID.

**URL**: `http://localhost:8080/api/drivers/{id}`

**Method**: `DELETE`

**Headers**: `Content-Type: application/json`

**Return data**:

```json
{
    "status": "success",
    "message": "Delete driver successfully",
    "data": {
        "id": "20240410230323",
        "name": "Tran Thi C",
        "address": "122 Le Van Sy, Quan Tan Binh, TP.HCM",
        "phone_number": "05418234567",
        "license": "A2",
        "cccd": "54865484654",
        "routeId": "TP.HCM - BaRiaVungTau",
        "status": "available",
        "vehicleId": "1",
        "vehicleType": "Truck",
        "email": "hothanhnhanhot@gmail.com"
    }
}
```

-- End of file --
