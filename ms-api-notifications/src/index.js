const express = require('express');
const cors = require('cors');
const app = express()


app.set('port', 3017);

app.use(cors({
    methods: '*',
    origin: '*',
    allowedHeaders: "*",
    credentials: false,
}));

app.get('/',(req, res) => {
    res.send('API Notifications v1.0')
})

app.listen(app.get('port'),(error) => { 
    if(error) {
        throw new Error('Hubo un error');
    }
    console.log('Iniciando ms-api-notifications');
});