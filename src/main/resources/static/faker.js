const { Client } = require('pg'); // Module pg pour interagir avec PostgreSQL
const faker = require('faker');   // Module Faker.js pour générer des données aléatoires

// Connexion à la base de données PostgreSQL
const client = new Client({
    host: 'localhost',
    port: 5432,
    user: 'your_user', // Remplace par ton nom d'utilisateur PostgreSQL
    password: 'your_password', // Remplace par ton mot de passe PostgreSQL
    database: 'commerce_api',
});

client.connect()
    .then(() => console.log('Connected to the database'))
    .catch(err => console.error('Connection error', err.stack));

// Génération et insertion de données pour la table 'users'
async function insertUsers() {
    for (let i = 0; i < 50; i++) {
        const username = faker.internet.userName();
        const email = faker.internet.email();
        const occupation = faker.name.jobTitle();
        const password = faker.internet.password();

        const query = `
            INSERT INTO users (username, email, occupation, password)
            VALUES ($1, $2, $3, $4);
        `;
        const values = [username, email, occupation, password];

        try {
            await client.query(query, values);
            console.log(`Inserted user ${i + 1}`);
        } catch (err) {
            console.error('Error inserting user:', err.stack);
        }
    }
}

// Génération et insertion de données pour la table 'product'
async function insertProducts() {
    const statuses = ['append', 'available', 'unavailable'];

    for (let i = 0; i < 50; i++) {
        const product_name = faker.commerce.productName();
        const status = statuses[Math.floor(Math.random() * statuses.length)];
        const price = parseFloat(faker.commerce.price());
        const category = faker.commerce.department();
        const description = faker.lorem.sentence();

        const query = `
            INSERT INTO product (product_name, status, price, category, description)
            VALUES ($1, $2, $3, $4, $5);
        `;
        const values = [product_name, status, price, category, description];

        try {
            await client.query(query, values);
            console.log(`Inserted product ${i + 1}`);
        } catch (err) {
            console.error('Error inserting product:', err.stack);
        }
    }
}

// Génération et insertion de données pour la table 'review'
async function insertReviews() {
    for (let i = 0; i < 50; i++) {
        const author = faker.name.findName();
        const id_product = Math.floor(Math.random() * 50) + 1; // ID produit entre 1 et 50
        const rating = Math.floor(Math.random() * 5) + 1; // Note entre 1 et 5
        const comment = faker.lorem.sentence();

        const query = `
            INSERT INTO review (author, id_product, rating, comment)
            VALUES ($1, $2, $3, $4);
        `;
        const values = [author, id_product, rating, comment];

        try {
            await client.query(query, values);
            console.log(`Inserted review ${i + 1}`);
        } catch (err) {
            console.error('Error inserting review:', err.stack);
        }
    }
}

// Génération et insertion de données pour la table 'promo'
async function insertPromos() {
    for (let i = 0; i < 50; i++) {
        const id_product = Math.floor(Math.random() * 50) + 1; // ID produit entre 1 et 50
        const expiration_date = faker.date.future();
        const category = faker.commerce.department();

        const query = `
            INSERT INTO promo (id_product, expiration_date, category)
            VALUES ($1, $2, $3);
        `;
        const values = [id_product, expiration_date, category];

        try {
            await client.query(query, values);
            console.log(`Inserted promo ${i + 1}`);
        } catch (err) {
            console.error('Error inserting promo:', err.stack);
        }
    }
}

// Génération et insertion de données pour la table 'client'
async function insertClients() {
    for (let i = 0; i < 50; i++) {
        const client_name = faker.name.findName();
        const phone_number = faker.phone.phoneNumber();
        const email = faker.internet.email();

        const query = `
            INSERT INTO client (client_name, phone_number, email)
            VALUES ($1, $2, $3);
        `;
        const values = [client_name, phone_number, email];

        try {
            await client.query(query, values);
            console.log(`Inserted client ${i + 1}`);
        } catch (err) {
            console.error('Error inserting client:', err.stack);
        }
    }
}

// Génération et insertion de données pour la table 'orders'
async function insertOrders() {
    for (let i = 0; i < 50; i++) {
        const order_date = faker.date.past();
        const status = Math.random() > 0.5; // 50% de chance d'être true ou false
        const quantity = Math.floor(Math.random() * 10) + 1; // Quantité entre 1 et 10
        const total_price = parseFloat(faker.commerce.price()) * quantity;
        const id_client = Math.floor(Math.random() * 50) + 1; // ID client entre 1 et 50
        const id_product = Math.floor(Math.random() * 50) + 1; // ID produit entre 1 et 50

        const query = `
            INSERT INTO orders (order_date, status, quantity, total_price, id_client, id_product)
            VALUES ($1, $2, $3, $4, $5, $6);
        `;
        const values = [order_date, status, quantity, total_price, id_client, id_product];

        try {
            await client.query(query, values);
            console.log(`Inserted order ${i + 1}`);
        } catch (err) {
            console.error('Error inserting order:', err.stack);
        }
    }
}

// Génération et insertion de données pour la table 'cart'
async function insertCarts() {
    for (let i = 0; i < 50; i++) {
        const reference = faker.datatype.number();
        const type_cart = faker.random.arrayElement(['regular', 'express']);
        const id_client = Math.floor(Math.random() * 50) + 1; // ID client entre 1 et 50

        const query = `
            INSERT INTO cart (reference, type_cart, id_client)
            VALUES ($1, $2, $3);
        `;
        const values = [reference, type_cart, id_client];

        try {
            await client.query(query, values);
            console.log(`Inserted cart ${i + 1}`);
        } catch (err) {
            console.error('Error inserting cart:', err.stack);
        }
    }
}

// Appel des fonctions pour insérer les données
async function insertData() {
    await insertUsers();
    await insertProducts();
    await insertReviews();
    await insertPromos();
    await insertClients();
    await insertOrders();
    await insertCarts();
    client.end();
}

insertData()
    .catch(err => console.error('Error during data insertion:', err.stack));
