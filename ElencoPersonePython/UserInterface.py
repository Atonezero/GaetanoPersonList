from os import system, name
from ListOfPersone import Elenco
from Persona import Persona

def singleton(cls):
    instances = {}
    def get_instance(*args, **kwargs):
        if cls not in instances:
            instances[cls] = cls(*args, **kwargs)
        return instances[cls]
    return get_instance

@singleton
class UserInterface:

    def __init__(self):
        self.__menu = ['1. Inserisci', '2. Cancella', '3. Cerca', '4. Modifica', '5 .Visualizza Elenco', '6 .Esci']
        
    
    def clear(self):
        if name == 'nt':
            _ = system('cls')
        else:
            _ = system('clear')

    def choice(self,):
        print(self.__menu)
        while(c<0 or c>5):
            print('>> ')
            c = int(input())
        return c
    
    def callMenu(self, elenco: Elenco):  
        c = -1
        while(c!=0):
            c=self.choice(c)

        match c:
            case 1:
                self.Inserisci(elenco)
            case 2:
                self.Cancella(elenco)
            case 3:
                self.Cerca(elenco)
            case 4:
                self.Modifica(elenco)
            case 5:
                self.Visualizza(elenco)
            case 6:
                exit()
            case default:
                print('Scelta non valida')

    def Inserisci(self, elenco: Elenco):    
        print('Inserisci nome: ')
        nome = input()
    
        print('Inserisci Data di Nascita: DD-MM-YYYY')
        DoB = input()
    
        p = Persona(nome, DoB)
        elenco.add(p)  

    def Cancella(self, elenco: Elenco):
       pass 

    def Cerca(self, elenco: Elenco):
        print('Inserisci nome: ')
        nome = input()
        print('Inserisci eta: ')
        eta = input()
        if elenco.search(Persona(nome, eta)) != None:
            print('Persona trovata')
            print('Trovato a posizone:  ' + elenco.search(Persona(nome, eta)))
        else:
            print('Persona non trovata')

    def Modifica(self, elenco: Elenco): 
        print('Inserisci nome: ')
        nome = input()
        print('Inserisci eta: ')
        eta = input()
        i = elenco.search(Persona(nome, eta)) 
        if i != None:
            print('Persona trovata')
            print('Trovato a posizone:  ' + i)
            print('Inserisci nuovo nome: ')
            nome = input()
            print('Inserisci nuova Data di Nascita: DD-MM-YYYY')
            DoB = input()
            elenco[i] = (Persona(nome, DoB))
        else:
            print('Persona non trovata')

    def Visualizza(self, elenco: Elenco):
        print(elenco)

if __name__ == '__main__':
    print('Errore, questo file non è eseguibile')
