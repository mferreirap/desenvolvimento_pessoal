const express = require('express')
const restful = require('node-restful')
const server = express()
const mongoose = restful.mongoose
const bodyParse = require('body-parser')
const cors = require('cors')

//Atribuiçao do banco de dados
mongoose.Promise = global.Promise
mongoose.connect('mongodb://db/mydb')

//Teste
server.get('/', (req,res, next) => res.send('Backend'))

//Middlewares
server.use(bodyParse.urlencoded({extended:true}))
server.use(bodyParse.json())
server.use(cors())

//ODM
const Client = restful.model('Client', {
    name: { type: String, required: true }
})

//Rest API
Client.methods(['get', 'post', 'put', 'delete'])
Client.updateOptions({new: true, runValidators: true})

//Routes
Client.register(server, '/clients')

//Iniciando o servidor
server.listen(3000)