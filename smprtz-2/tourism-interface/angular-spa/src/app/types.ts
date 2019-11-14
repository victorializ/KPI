export interface Equipment {
    id: number;
    name: string;
    price: string;
    type: string;
}

export interface Tourist {
    id: number
    name: string;
    email: string;
    password: string;
}

export interface Order {
    id: number;
    date: string;
    tourist: Tourist;
    equipment: Equipment;
}