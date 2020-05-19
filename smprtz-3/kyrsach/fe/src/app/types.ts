export interface Equipment {
    id: number;
    name: string;
    price: string;
    type: string;
}

export interface Tourist {
    id: number;
    name: string;
    email: string;
    password: string;
    role: Roles;
}

export interface Auth {
    user: Tourist;
    token: string;
}

export interface Order {
    id: number;
    date: string;
    tourist: Tourist;
    equipment: Equipment;
}

export enum Roles { 
    admin = "ROLE_ADMIN",
    client = "ROLE_CLIENT"
}