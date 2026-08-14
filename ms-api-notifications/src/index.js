const http = require('http');
const morgan = require('morgan');
const express = require('express');
const cors = require('cors');
const app = express();

app.set('hostname', 'localhost');
app.set('port', 3017);

app.use(cors({
    methods: '*',
    origin: '*',
    allowedHeaders: "*",
    credentials: false,
}));
app.use(express.json());
app.use(express.urlencoded({extended: true}));
app.use(morgan('dev'));

app.get('/',(req, res) => {
    res.send('API Notifications v1.0')
})

// Modo de desarrollo local
http.createServer(app)
    .listen(app.get('port'), app.get('hostname'), (err) => {
        if(!err?.message) {
            const path = `http://${app.get('hostname')}:${app.get('port')}`;
            console.log(`API delployed: ${path}`);
        } else {
            console.log(err?.message);
        }
    });