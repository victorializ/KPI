#!/bin/sh

echo users list 
curl -X GET http://localhost:8080/tourist/list -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlbWFpbEBlbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9DTElFTlQiLCJpYXQiOjE1NzYxMDg0MjIsImV4cCI6MTU3NjExMjAyMn0.e6jSSdY4ZFX8YvvmKUrubm6LGXPwCt8m8e9QVK3KTkk'  

echo
echo create order
curl -d 'touristId=58&equipmentId=31' -X POST http://localhost:8080/order/new -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlbWFpbEBlbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9DTElFTlQiLCJpYXQiOjE1NzYxMDg0MjIsImV4cCI6MTU3NjExMjAyMn0.e6jSSdY4ZFX8YvvmKUrubm6LGXPwCt8m8e9QVK3KTkk'

echo
echo users orders
curl -X GET http://localhost:8080/order/list/58 -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlbWFpbEBlbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9DTElFTlQiLCJpYXQiOjE1NzYxMDg0MjIsImV4cCI6MTU3NjExMjAyMn0.e6jSSdY4ZFX8YvvmKUrubm6LGXPwCt8m8e9QVK3KTkk'
