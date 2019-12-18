const graphql = require('graphql');
const Order = require('./model');
const Product = require('../product/model');
const product = require('../product/index');
const User = require('../user/model');
const user = require('../user/index');

const { 
    GraphQLObjectType, 
    GraphQLID, 
    GraphQLList, 
    GraphQLNonNull
} = graphql;

const type = new GraphQLObjectType({
    name: 'Order', 
    fields: () => ({
        id: { type: GraphQLID  },
        user: {
            type: user.type,
            resolve(parent, args) {
                return User.findById(parent.userId);
            }
        }, 
        product: {
            type: product.type,
            resolve(parent, args) {
                return Product.findById(parent.productId);
            }
        }
    })
});

exports.query = {
    order:{
        type,
        args: { id: { type: GraphQLID } },
        resolve(parent, args) {
            return Order.findById(args.id);
        }
    },
    orders:{
        type: new GraphQLList(type),
        resolve(parent, args) {
            return Order.find({});
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