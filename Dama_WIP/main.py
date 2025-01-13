from dama import dama
from ai import Ai
from ui import UI

def main():
    # Inizializza il gioco e l'IA
    game = dama()
    ai = Ai(game)
    
    # Inizializza l'interfaccia utente con il gioco e l'IA
    ui = UI(game, ai)

    ui.run()

if __name__ == "__main__":
    main()
