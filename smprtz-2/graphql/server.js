var express = require('express');
var graphqlHTTP = require('express-graphql');

const schema = require('./graphqlschema');
const db = require('./db');

const app = express();

db.connect();

app.use('/graphql', graphqlHTTP({
  schema,
  graphiql: true,
}));

app.listen(4000, () => console.log('Now browse to localhost:4000/graphql'));