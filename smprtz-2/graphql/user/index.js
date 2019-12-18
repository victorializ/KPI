const graphql = require('graphql');
const User = require('./model');
const Order = require('../order/model');
const order = require('../order/index');

const { 
    GraphQLObjectType, 
    GraphQLString, 
    GraphQLID, 
    GraphQLList, 
    GraphQLNonNull
} = graphql;

const type = new GraphQLObjectType({
    name: 'User',
    fields: () => ({
        id: { type: GraphQLID  },
        name: { type: GraphQLString }, 
        email: { type: GraphQLString },
        order: {
            type: new GraphQLList(order.type),
            resolve(parent, args){
                return Order.find({ userId: parent.id });
            }
        }
    })
});

exports.query = {
    user: {
        type,
        args: { id: { type: GraphQLID } },
        resolve(parent, args) {
            return Book.findById(args.id);
        }
    },
    users:{
        type: new GraphQLList(type),
        resolve(parent, args) {
            return User.find({});
        }
    }
};

exports.migraton = {
    addUser: {
        type,
        args: {
            name: { type: new GraphQLNonNull(GraphQLString) },
            email: { type: new GraphQLNonNull(GraphQLString) }
        },
        resolve(parent, {name, email}) {
            const user = new User({
                name,
                email
            });
            return user.save();
        }
    }
};

exports.type = type;