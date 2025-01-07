from ListOfPersone import Elenco
from UserInterface import UserInterface 
from Persona import Persona
from os import system, name

def main():
    ui = UserInterface()
    elenco = Elenco()
    ui.callMenu(elenco)
        
if __name__ == '__main__':
    main()
11