from ListOfPersone import Elenco
from UserInterface import UserInterface 
from Persona import Persona
from os import system, name

def main():
    UI = UserInterface()
    elenco = Elenco()
    UI.callMenu(elenco)
        
if __name__ == '__main__':
    main()
