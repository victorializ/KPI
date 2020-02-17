#!/bin/sh

echo tickets list
curl -X GET http://localhost:8081/ticket/list 
echo
echo create ticket
curl -d 'event=ICE3PEAK&date=03/04/2020 17:00:00&location=Secret Place&zone=fan&place=5&price=400' -X POST http://localhost:8081/ticket/new
echo
echo find tickets by event
curl -X GET http://localhost:8081/ticket/find/ICE3PEAK
echo
curl -d 'event=ICE3PEAK&date=04/04/2020 17:00:00&location=Some Place&zone=fan&place=5&price=400' -X POST http://localhost:8081/ticket/update
echo
echo tickets list
curl -X GET http://localhost:8081/ticket/list 
echo
