from ListOfPersone import Elenco
from Persona import persona
from os import system, name

def clear():
    if name == 'nt':
        _ = system('cls')
    else:
        _ = system('clear')

def Inserisci():
    pass

def Cancella():
    pass

def Cerca():
    pass

def Modifica():
    pass

def Visualizza():
    pass

def menu():
    c = -1
    print('1: Inserisci')
    print('2: Cancella')
    print('3: Cerca')
    print('4: Modifica')
    print('5: Visualizza Elenco')
    print('6: Esci')
    print('>> ')
    
    while(c<0 or c>5):
        print('>> ')
        c = int(input())

    return c

def main():
    c=1
    while(c!=0):
        c=menu()

    match c:
        case 1:
            Inserisci()
        case 2:
            Cancella()
        case 3:
            Cerca()
        case 4:
            Modifica()
        case 5:
            Visualizza()
        
if __name__ == '__main__':
    main()
