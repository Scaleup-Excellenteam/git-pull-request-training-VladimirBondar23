let employee = {
    name: 'John Smith',
    salary: 50_000,
    address: {
    street: 'Flinders st',
    city: 'Melbourne',
    zipCode: 3144,
    },
    };

type Address = {
    street: string,
    city: string,
    zipcode: number
}

interface Employee {
    name: string;
    salary: number;
    address: Address;
}