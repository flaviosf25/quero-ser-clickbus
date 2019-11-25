
# API Execution Instructions

## Create a place
POST >> http://localhost:8080/places

POST >> https://ws-places-api.herokuapp.com/places

{

	"name": "",
	"slug": "",
	"city": {"name": ""},
	"state": {"name": ""}
		
}

## Edit a place
PUT >> http://localhost:8080/places

PUT >> https://ws-places-api.herokuapp.com/places

{

	"id": ,
	"name": "",
	"slug": "",
	"city": {"name": ""},
	"state": {"name": ""}
		
}

## Get a specific place
GET >> http://localhost:8080/places/{ID}

GET >> https://ws-places-api.herokuapp.com/places/{ID}

## List places and filter them by name
GET >> http://localhost:8080/places

GET >> https://ws-places-api.herokuapp.com/places

GET >> http://localhost:8080/places/search/{NAME}

GET >> https://ws-places-api.herokuapp.com/places/search/{NAME}

## Delete a place (Optional)
DELETE >> http://localhost:8080/places/{ID}

DELETE >> https://ws-places-api.herokuapp.com/places/{ID}
