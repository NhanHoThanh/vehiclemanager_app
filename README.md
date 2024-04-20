# Vehicle API
## Vehicle
### Get vehicle by id

**URL**: `http://localhost:8081/api/vehicle/{id}`

**Method**: `GET`

**Return**: vehicle

### Get all vehicle

**URL**: `http://localhost:8081/api/vehicle`

**Method**: `GET`

**Return**: list vehicle

### Get vehicle by departure || destination || timeStart || vehicleType

**URL**: `http://localhost:8081/api/vehicle/search`

**Method**: `GET`

**Return**: list vehicle

### Get vehicle by departure && destination

**URL**: `http://localhost:8081/api/vehicle/searchroute?departure=HoChiMinh&&destination=VungTau`

**Method**: `GET`

**Return**: list vehicle

### Update vehicle

**URL**: `http://localhost:8081/api/vehicle/{idVehicle}`

**Method**: `Put`

**Return**: vehicle

### Create vehicle

**URL**: `http://localhost:8081/api/vehicle`

**Method**: `Post`

**Return**: vehicle

### Add driver to vehicle

**URL**: `http://localhost:8081/api/vehicle/addDriver/{idVehicle}/{idDriver}`

**Method**: `Post`

**Return**: vehicle

### Remove driver vehicle

**URL**: `http://localhost:8081/api/vehicle/removeDriver/{idVehicle}/{idDriver}`

**Method**: `Delete`

**Return**: vehicle

### Remove vehicle

**URL**: `http://localhost:8081/api/vehicle/{idDriver}`

**Method**: `Delete`

**Return**: vehicle

### Calculate Total Revenue Cost Profit

**URL**: `http://localhost:8081/api/vehicle/totalRevenue/{idVehicle}`

**Method**: `PUT`

**Return**: vehicle

### Update Route And Calculate Income

**URL**: `http://localhost:8081/api/vehicle/updateRoute/{idVehicle}`

**Method**: `PUT`

**Return**: vehicle

### Get List Route History

**URL**: `http://localhost:8081/api/vehicle/history/route/{idVehicle}`

**Method**: `GET`

**Return**: list route

### Get List Income History

**URL**: `http://localhost:8081/api/vehicle/history/Income/{idVehicle}`

**Method**: `GET`

**Return**: list income

## Coach

### Get coach by id

**URL**: `http://localhost:8081/api/coach/{idCoach}`

**Method**: `GET`

**Return**: coach

### Get all coach

**URL**: `http://localhost:8081/api/coach/{idCoach}`

**Method**: `GET`

**Return**: list coach

### Get list passenger

**URL**: `http://localhost:8081/api/coach/listPassenger/{idVehicle}`

**Method**: `GET`

**Return**: list passenger

### Get coach by departure || destination || timeStart || vehicleType

**URL**: `http://localhost:8081/api/coach/search`

**Method**: `GET`

**Return**: list coach

### Create coach

**URL**: `http://localhost:8081/api/coach`

**Method**: `POST`

**Return**: coach

### Update coach

**URL**: `http://localhost:8081/api/coach/{idCoach}`

**Method**: `PUT`

**Return**: coach

### Add passenger

**URL**: `http://localhost:8081/api/coach/addPassenger/{idVehicle}/{idPassenger}`

**Method**: `POST`

**Return**: coach

### Remove passenger

**URL**: `http://localhost:8081/api/coach/removePassenger/{idVehicle}/{idPassenger}`

**Method**: `DELETE`

**Return**: coach

## Container

### Get container by id

**URL**: `http://localhost:8081/api/container/{idContainer}`

**Method**: `GET`

**Return**: container

### Get all container

**URL**: `http://localhost:8081/api/container`

**Method**: `GET`

**Return**: list container

### Get all cargo

**URL**: `http://localhost:8081/api/container/cargo`

**Method**: `GET`

**Return**: list cargo

### Get list cargo in container

**URL**: `http://localhost:8081/api/container/listCargo/{idVehicle}`

**Method**: `GET`

**Return**: list cargo

### Get list container by attributes

**URL**: `http://localhost:8081/api/container/listCargo/{idVehicle}`

**Method**: `GET`

**Return**: list container

### Create container

**URL**: `http://localhost:8081/api/container`

**Method**: `POST`

**Return**: container

### Update container

**URL**: `http://localhost:8081/api/container/{idContainer}`

**Method**: `PUT`

**Return**: container

### Add Cargo

**URL**: `http://localhost:8081/api/container/addCargo/{idVehicle}/{idCargo}`

**Method**: `POST`

**Return**: cargo

### Remove Cargo

**URL**: `http://localhost:8081/api/container/removeCargo/{idVehicle}/{idCargo}`

**Method**: `DELETE`

**Return**: cargo

## Cargo

### Get cargo by id

**URL**: `http://localhost:8081/api/cargo/{idCargo}`

**Method**: `GET`

**Return**: cargo

### Get all cargo

**URL**: `http://localhost:8081/api/cargo`

**Method**: `GET`

**Return**: list cargo

### Get cargo by attribute

**URL**: `http://localhost:8081/api/cargo/search`

**Method**: `GET`

**Return**: list cargo

### Create cargo

**URL**: `http://localhost:8081/api/cargo`

**Method**: `POST`

**Return**: container

### Update cargo

**URL**: `http://localhost:8081/api/cargo/{idCargo}`

**Method**: `PUT`

**Return**: cargo

### Delete cargo

**URL**: `http://localhost:8081/api/cargo/{idCargo}`

**Method**: `DELETE`

**Return**: cargo

## Passenger

### Get Passenger by id

**URL**: `http://localhost:8081/api/passenger/{idPassenger}`

**Method**: `GET`

**Return**: passenger

### Get all Passenger

**URL**: `http://localhost:8081/api/passenger`

**Method**: `GET`

**Return**: list passenger

### Get passenger by attribute

**URL**: `http://localhost:8081/api/assenger/search`

**Method**: `GET`

**Return**: list passenger

### Create passenger

**URL**: `http://localhost:8081/api/passenger`

**Method**: `POST`

**Return**: passenger

### Update passenger

**URL**: `http://localhost:8081/api/passenger/{idPassenger}`

**Method**: `PUT`

**Return**: passenger

### Delete Passenger

**URL**: `http://localhost:8081/api/passenger/{idPassenger}`

**Method**: `DELETE`

**Return**: passenger
