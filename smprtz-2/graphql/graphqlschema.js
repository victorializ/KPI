const graphql = require('graphql');
const user = require('./user');
const product = require('./product');
const order = require('./order');

const { 
    GraphQLObjectType, 
    GraphQLSchema
} = graphql;

const RootQuery = new GraphQLObjectType({
    name: 'RootQueryType',
    fields: {
        ...user.query,
        ...product.query,
        ...order.query
    }
});

const Mutation = new GraphQLObjectType({
    name: 'Mutation',
    fields: {
        ...user.migraton,
        ...product.migraton,
        ...order.migraton
    }
});

module.exports = new GraphQLSchema({
    query: RootQuery,
    mutation: Mutation
}); 