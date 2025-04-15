const express = require('express');
const fs = require('fs');
const app = express();
const PORT = 3000;

app.use(express.json());

// Percorso per salvare un nuovo segnaposto
app.post('/api/segnaposto', (req, res) => {
    const nuovoSegnaposto = req.body;

    // Leggi i segnaposti già salvati
    fs.readFile('segnaposti.json', 'utf8', (err, data) => {
        let segnaposti = [];
        if (!err && data) {
            segnaposti = JSON.parse(data);
        }

        // Aggiungi il nuovo segnaposto
        segnaposti.push(nuovoSegnaposto);

        // Salva tutto nel file
        fs.writeFile('segnaposti.json', JSON.stringify(segnaposti, null, 2), (err) => {
            if (err) {
                return res.status(500).send('Errore nel salvataggio');
            }
            res.send({ messaggio: 'Segnaposto salvato con successo!' });
        });
    });
});

// Percorso per leggere tutti i segnaposti
app.get('/api/segnaposti', (req, res) => {
    fs.readFile('segnaposti.json', 'utf8', (err, data) => {
        if (err) {
            return res.status(500).send('Errore nella lettura');
        }
        res.send(JSON.parse(data || '[]'));
    });
});

app.listen(PORT, () => {
    console.log(`Server in ascolto su http://localhost:${PORT}`);
});
