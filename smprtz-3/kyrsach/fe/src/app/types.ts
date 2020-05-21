export interface Equipment {
    id: number;
    name: string;
    price: string;
    type: string;
    rating: number;
    description: string;
}

export interface User {
    id: number;
    name: string;
    email: string;
    password: string;
    role: Roles;
}

export interface Auth {
    user: User;
    token: string;
}

export interface Order {
    id: number;
    date: string;
    touristId: string;
    equipmentId: string;
    equipment: Equipment;
}

export interface Feedback {
    id: number;
    date: string;
    rating: number;
    text: string;
    equipmentId: number;
}

export enum Roles { 
    admin = "ROLE_ADMIN",
    client = "ROLE_CLIENT"
}