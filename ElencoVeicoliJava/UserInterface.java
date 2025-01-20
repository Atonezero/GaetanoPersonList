    package ElencoVeicoliJava;

    public class UserInterface {
        private ElencoVeicoli _manager;
        private IinputOutput _io;

        public UserInterface(IinputOutput io, ElencoVeicoli manager) {
            this._io = io;
            this._manager = manager;
        }

        public void clearConsole() {
            try {
                final String os = System.getProperty("os.name").toLowerCase();
                if (os.contains("win")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
            } catch (Exception e) {
                System.out.println("Error clearing the console.");
            }
        }

        public int callMenu() {
            clearConsole();
            _io.println("\n=== Menu ===");
            _io.println("1. Aggiungi Veicolo");
            _io.println("2. Elimina Veicolo");
            _io.println("3. Mostra lista Veicoli");
            _io.println("4. Modifica Veicolo");
            _io.println("5. Carica da file");
            _io.println("6. Salva su file");
            _io.println("7. Exit");
            _io.print("Seleziona un'opzione: ");

            int choice;
            while (true) {
                try {
                    choice = _io.inputInt();
                    if (choice >= 1 && choice <= 7) break;
                    _io.println("Opzione non valida. Riprova.");
                } catch (NumberFormatException e) {
                    _io.println("Input non valido. Inserisci un numero.");
                }
            }
            return choice;
        }

        private void MenuOfVeicoli() {
            _io.println("Lista Tipi di Veicoli:");
            _io.println("1. Quadriciclo");
            _io.println("2. Motociclo");
            _io.println("3. Mezzo Pesante");
        }

        private void AddVeicolo(ElencoVeicoli _manager) {
            clearConsole();
            MenuOfVeicoli();
            _io.println("Scegli il tipo di veicolo da aggiungere:");
            int scelta = _io.inputInt();

            _io.println("Inserisci il numero di ruote:");
            String ruote = _io.input();

            _io.println("Inserisci la marca:");
            String marca = _io.input();

            _io.println("Inserisci la targa:");
            String targa = _io.input();
            
            _io.println("Inserisci la Cilindarata:");
            String cilindrata = _io.input();

            String numeroPorte = null, pesoMassimo = null, haBauletto = null;

            if(scelta == 1){
                _io.println("Inserisci il numero di porte:");
                numeroPorte = _io.input();
            }
            else if(scelta == 2) {
                _io.println("Presenza Bauletto (true/false):");
                haBauletto = _io.input();
            }
            else if(scelta == 3){
                _io.println("Peso massimo supportato:");
                pesoMassimo = _io.input();
            }

            try {
                Veicolo veicolo = FactoryVeicoli.creaVeicolo(scelta, ruote, marca, cilindrata, targa, numeroPorte, Boolean.parseBoolean(haBauletto), pesoMassimo);
                _manager.AddVeicolo(veicolo);
                _io.println("Veicolo aggiunto con successo.");
            } catch (IllegalArgumentException e) {
                _io.println(e.getMessage());
            }
        }

        private int trovaVeicolo(ElencoVeicoli _manager, String targa) {
            for (int i = 0; i < _manager.numeroVeicoli(); i++) {
                if (_manager.getVeicolo(i).getTarga().equalsIgnoreCase(targa)) {
                    _io.println("Veicolo trovato: " + _manager.getVeicolo(i).toString());
                    return i;
                }
            }
            _io.println("Veicolo con targa " + targa + " non trovato.");
            return -1;
        }

        private void DeledeVeicolo(ElencoVeicoli _manager) {
            clearConsole();
            _io.println("Inserisci la targa del veicolo da eliminare:");
            String targa = _io.input();

            int index = trovaVeicolo(_manager, targa);
            if (index != -1) {
                _manager.removeVeicolo(index);
                _io.println("Veicolo eliminato con successo.");
            } else {
                _io.println("Errore: veicolo non trovato.");
            }
        }

        private void ModificaVeicolo(ElencoVeicoli _manager) {
            clearConsole();
            _io.println("Inserisci la targa del veicolo da modificare:");
            String targa = _io.input();

            int index = trovaVeicolo(_manager, targa);
            if (index != -1) {
                Veicolo veicolo = _manager.getVeicolo(index);

                _io.println("Modifica i dati del veicolo (premi Invio per mantenere il valore corrente):");
                _io.println("");
                
                _io.println("Ruote attuale: " + veicolo.getRuote());
                _io.print("Nuova Ruote:");
                String nuovaRuote = _io.input();
                if (!nuovaRuote.isEmpty()) veicolo.setMarca(nuovaRuote);

                _io.println("Marca attuale: " + veicolo.getMarca());
                _io.print("Nuova marca:");
                String nuovaMarca = _io.input();
                if (!nuovaMarca.isEmpty()) veicolo.setMarca(nuovaMarca);

                _io.println("Cilindrata attuale: " + veicolo.getCilindrata());
                _io.print("Nuovo cilindrata:");
                String nuovoCilindrata = _io.input();
                if (!nuovoCilindrata.isEmpty()) veicolo.setCilindrata(nuovoCilindrata);

                _io.println("Targa attuale: " + veicolo.getTarga());
                _io.print("Nuova targa:");
                String nuovaTarga = _io.input();
                if (!nuovaTarga.isEmpty()) veicolo.setTarga(nuovaTarga);

                _io.println("Veicolo modificato con successo.");
            } else {
                _io.println("Errore: veicolo non trovato.");
            }
        }

        public void runMenu() {
            while (true) {
                int choice = callMenu();
                if (choice == 1) {
                    AddVeicolo(_manager);
                } else if (choice == 2) {
                    DeledeVeicolo(_manager);
                } else if (choice == 3) {
                    _manager.stampaVeicoli();
                } else if (choice == 4) {
                    ModificaVeicolo(_manager);
                } else if (choice == 5) {
                    _manager.setVeicoli(FileManager.caricaDaFile("Elenco di Veicoli.txt", new FileManager.FileParser<Veicolo>() {
                    @Override
                    public Veicolo parse(String line) {
                        try {
                            // Rimuovi spazi aggiuntivi prima e dopo la riga
                            line = line.trim();
                    
                            // Dividi i campi eliminando spazi superflui
                            String[] parts = line.split(",\\s*");
                    
                            if (parts.length < 5) {
                                throw new IllegalArgumentException("Riga malformattata o incompleta: " + line);
                            }
                    
                            // Identifica il tipo di veicolo
                            String tipoVeicolo = parts[0].trim();
                    
                            // Campi comuni
                            String ruote = parts[1].trim();
                            String marca = parts[2].trim();
                            String cilindrata = parts[3].trim();
                            String targa = parts[4].trim();
                    
                            // Identifica e crea il veicolo in base al tipo
                            switch (tipoVeicolo) {
                                case "Quadriciclo":
                                    if (parts.length != 6) {
                                        throw new IllegalArgumentException("Riga malformattata per Quadriciclo: " + line);
                                    }
                                    String numeroPorte = parts[5].trim();
                                    return new Quadriciclo(ruote, marca, cilindrata, targa, numeroPorte);
                    
                                case "Motociclo":
                                    if (parts.length != 6) {
                                        throw new IllegalArgumentException("Riga malformattata per Motociclo: " + line);
                                    }
                                    boolean haBauletto = Boolean.parseBoolean(parts[5].trim());
                                    return new Motocicli(ruote, marca, cilindrata, targa, haBauletto);
                    
                                case "Mezzo_pesante":
                                    if (parts.length != 6) {
                                        throw new IllegalArgumentException("Riga malformattata per Mezzo Pesante: " + line);
                                    }
                                    String pesoMassimo = parts[5].trim();
                                    return new Mezzi_Pesanti(ruote, marca, cilindrata, targa, pesoMassimo);
                    
                                default:
                                    throw new IllegalArgumentException("Tipo di veicolo sconosciuto: " + tipoVeicolo);
                            }
                        } catch (Exception e) {
                            throw new IllegalArgumentException("Errore durante il parsing della riga: " + line, e);
                        }
                    }
                    
                        
                }));
                    _io.println("Caricamento riuscito...");
                    _io.input();
                } else if (choice == 6) {
                    // Salva i veicoli su file
                    FileManager.salvaSuFile("Elenco di Veicoli.txt", _manager.getVeicoli(), new FileManager.FileFormatter<Veicolo>() {
                        @Override
                        public String format(Veicolo v) {
                            String base = v.getRuote() + ", " + v.getMarca() + ", " + v.getCilindrata() + ", " + v.getTarga();
        
                            if (v instanceof Motocicli) {
                                Motocicli motociclo = (Motocicli) v;
                                return "Motociclo, " + base + ", " + motociclo.isBauletto();
                            } else if (v instanceof Quadriciclo) {
                                Quadriciclo quadriciclo = (Quadriciclo) v;
                                return "Quadriciclo, " + base + ", " + quadriciclo.getPorte();
                            } else if (v instanceof Mezzi_Pesanti) {
                                Mezzi_Pesanti mezzoPesante = (Mezzi_Pesanti) v;
                                return "Mezzo_pesante, " + base + ", " + mezzoPesante.getPesoSupportato();
                            }
                            
                            return base;
                        }
                    });
                    _io.println("Veicoli salvati su file. Premi per continuare ...");
                    _io.input();
                } else if (choice == 7) {
                    _io.println("Uscita dal programma.");
                    return;
                } else {
                    _io.println("Errore: scelta non valida.");
                }
            }
        }
    }