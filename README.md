# Route_Income API
## Route
### Get route by id

**URL**: `http://localhost:8082/api/route/{id}`

**Method**: `GET`

**Return**: route

### Get all route

**URL**: `http://localhost:8082/api/route`

**Method**: `GET`

**Return**: list route


### Update route

**URL**: `http://localhost:8082/api/route/{idRoute}`

**Method**: `Put`

**Return**: route

### Create route

**URL**: `http://localhost:8082/api/route`

**Method**: `Post`

**Return**: route

### Check route

**URL**: `http://localhost:8082/api/route/checkRoute/{idVehicle}`

**Method**: `Get`

**Return**: Boolean

## Income

### Get income by id

**URL**: `http://localhost:8082/api/income/{idIncome}`

**Method**: `GET`

**Return**: income

### Get all income

**URL**: `http://localhost:8082/api/income/{idIncome}`

**Method**: `GET`

**Return**: list income

### Create income

**URL**: `http://localhost:8082/api/income`

**Method**: `POST`

**Return**: income

### Update income

**URL**: `http://localhost:8082/api/income/{idIncome}`

**Method**: `PUT`

**Return**: income

### Delete

**URL**: `http://localhost:8082/api/income/{idIncome}`

**Method**: `DELETE`

**Return**: income

### Calculate Income Form Specific Coach Test Method

**URL**: `http://localhost:8082/api/income/getIncome/test/{idVehicle}`

**Method**: `GET`

**Return**: income

### Calculate Income From Specific Coach

**URL**: `http://localhost:8082/api/income/getIncome/{idVehicle}`

**Method**: `GET`

**Return**: coach

### Get Route Form Income

**URL**: `http://localhost:8082/api/income/getIncome/route/{idVehicle}`

**Method**: `GET`

**Return**: route

### Ccalculate Income Form Coach

**URL**: `http://localhost:8082/api/income/getIncomeCoach/{idVehicle}`

**Method**: `GET`

**Return**: Income

### Calculate Income Form Container

**URL**: `http://localhost:8082/api/income/getIncomeContainer/{idVehicle}`

**Method**: `GET`

**Return**: income

### Add Coach Income

**URL**: `http://localhost:8082/api/income/add/IncomeCoach/{idVehicle}`

**Method**: `GET`

**Return**: String

### Add Container Income

**URL**: `http://localhost:8082/api/income/add/IncomeContainer/{idVehicle}`

**Method**: `GET`

**Return**: String
