from os import system, name
from ListOfPersone import Elenco
import sys
from Persona import Persona
from singleton import singleton
from datetime import datetime

@singleton
class UserInterface:

    def __init__(self):
        self.__menu = ['1. Inserisci', '2. Cancella', '3. Cerca', '4. Modifica', '5 .Visualizza Elenco', '6 .Esci']
        
    
    def clear(self):
        if name == 'nt':
            _ = system('cls')
        else:
            _ = system('clear')

    def choice(self, c : int):

        while(c < 1 or c > 6):
            print('>> ')
            c = int(input())
        return c
    
    def callMenu(self, elenco: Elenco):
        while True:
            self.clear()
            print("\n".join(self.__menu))
            try:
                c = int(input("\nSeleziona un'opzione: "))
                if c == 1:
                    self.Inserisci(elenco)
                elif c == 2:
                    self.Cancella(elenco)
                elif c == 3:
                    self.Cerca(elenco)
                elif c == 4:
                    self.Modifica(elenco)
                elif c == 5:
                    self.Visualizza(elenco)
                elif c == 6:
                    print("Uscita...")
                    sys.exit()
                else:
                    print("Opzione non valida!")
            except ValueError:
                print("Inserisci un numero valido.")
            input("\nPremi Invio per continuare...")


    def Inserisci(self, elenco: Elenco):    
        nome = input('Inserisci nome: ')
        while True:
            DoB = input('Inserisci Data di Nascita (DD-MM-YYYY): ')
            try:
                datetime.strptime(DoB, '%d-%m-%Y')
                break
            except ValueError:
                print("Data non valida. Riprova.")

        p = Persona(nome, DoB)
        elenco.add(p)
        print("Persona aggiunta con successo.")

    def Cancella(self, elenco: Elenco):
        persona = self.Cerca(elenco)
        elenco.delede(persona)
        print("Persona cancellata con successo.")

    def Cerca(self, elenco: Elenco):
        print('Inserisci nome: ')
        nome = input()
        while True:
            DoB = input('Inserisci Data di Nascita (DD-MM-YYYY): ')
            try:
                datetime.strptime(DoB, '%d-%m-%Y')
                break
            except ValueError:
                print("Data non valida. Riprova.")

        if elenco.search(Persona(nome, DoB)) != None:
            print('Persona trovata')
            #print('Trovato a posizone:  ' + str(elenco.search(Persona(nome, DoB))) )
            return Persona(nome, DoB)
        else:
            print('Persona non trovata')
            return None
    
    def Modifica(self, elenco: Elenco): 
        nome = input('Inserisci nome da modificare: ')
        while True:
            DoB = input('Inserisci Data di Nascita (DD-MM-YYYY): ')
            try:
                datetime.strptime(DoB, '%d-%m-%Y')
                break
            except ValueError:
                print("Data non valida. Riprova.")
        persona = Persona(nome, DoB)
        elenco.search(persona)
        
        if persona:
            print(f"Modificando: {persona}")
            elenco.delede(persona)
            nuovo_nome = input('Inserisci nuovo nome: ')
            nuovo_DoB = input('Inserisci nuova Data di Nascita (DD-MM-YYYY): ')
            persona.nome = nuovo_nome
            persona.DoB = nuovo_DoB
            elenco.add(persona)
            print("Persona modificata con successo.")
        else:
            print("Persona non trovata.")


    def Visualizza(self, elenco: Elenco):
        print(elenco)

if __name__ == '__main__':
    print('Errore, questo file non è eseguibile')
