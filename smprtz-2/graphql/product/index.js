const graphql = require('graphql');
const Product = require('./model');
const Order = require('../order/model');
const order = require('../order/index');

const { 
    GraphQLObjectType, 
    GraphQLString, 
    GraphQLID, 
    GraphQLInt,
    GraphQLList, 
    GraphQLNonNull
} = graphql;

const type = new GraphQLObjectType({
    name: 'Product',
    fields: () => ({
        id: { type: GraphQLID },
        name: { type: GraphQLString },
        price: { type: GraphQLInt },
        order: {
            type: new GraphQLList(order.type),
            resolve(parent, args){
                return Order.find({ productId: parent.id });
            }
        }
    })
});

exports.query = {
    product:{
        type,
        args: { id: { type: GraphQLID } },
        resolve(parent, args) {
            return Product.findById(args.id);
        }
    },
    products:{
        type: new GraphQLList(type),
        resolve(parent, args) {
            return Product.find({});
        }
    }
};

exports.migraton = {
    addOrder: {
        type,
        args:{
            userId: { type: new GraphQLNonNull(GraphQLID)},
            productId: { type: new GraphQLNonNull(GraphQLID)}
        },
        resolve(parent, {userId, productId}){
            const order = new Order({
                userId,
                productId
            })
            return order.save();
        }
    }
};

exports.type = type;