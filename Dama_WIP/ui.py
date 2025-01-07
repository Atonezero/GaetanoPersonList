import pygame
from dama import dama
from ai import Ai

class UI:
    def __init__(self, game, ai):
        self.game = game
        self.ai = ai
        self.width = 800
        self.height = 800
        self.square_size = self.width // 8
        self.colors = [(255, 255, 255), (0, 0, 0)] 
        self.piece_colors = {1: (255, 0, 0), -1: (0, 0, 255)} 
        self.selected_piece = None
        self.selected_pos = None
        self.turn = 1 
        pygame.init()
        self.screen = pygame.display.set_mode((self.width, self.height))
        pygame.display.set_caption("Dama Game")
        self.font = pygame.font.Font(None, 36)

    def draw_board(self):
        for row in range(8):
            for col in range(8):
                color = self.colors[(row + col) % 2]
                pygame.draw.rect(self.screen, color, (col * self.square_size, row * self.square_size, self.square_size, self.square_size))

    def draw_pieces(self):
        for row in range(8):
            for col in range(8):
                piece = self.game.board[row][col]
                if piece != 0:
                    pygame.draw.circle(self.screen, self.piece_colors[piece], (col * self.square_size + self.square_size // 2, row * self.square_size + self.square_size // 2), self.square_size // 3)

    def draw_menu(self):
        self.screen.fill((0, 0, 0))
        title_text = self.font.render("Dama - Seleziona Difficoltà", True, (255, 255, 255))
        easy_text = self.font.render("1. Facile", True, (255, 255, 255))
        medium_text = self.font.render("2. Medio", True, (255, 255, 255))
        hard_text = self.font.render("3. Difficile", True, (255, 255, 255))

        self.screen.blit(title_text, (self.width // 2 - title_text.get_width() // 2, 100))
        self.screen.blit(easy_text, (self.width // 2 - easy_text.get_width() // 2, 200))
        self.screen.blit(medium_text, (self.width // 2 - medium_text.get_width() // 2, 300))
        self.screen.blit(hard_text, (self.width // 2 - hard_text.get_width() // 2, 400))
        pygame.display.flip()

    def show_victory(self, winner):
        self.screen.fill((0, 0, 0))
        victory_text = self.font.render(f"{winner} ha vinto!", True, (255, 255, 255))
        self.screen.blit(victory_text, (self.width // 2 - victory_text.get_width() // 2, self.height // 2))
        pygame.display.flip()
        pygame.time.wait(3000)

    def run_menu(self):
        running = True
        while running:
            self.draw_menu()
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    running = False
                    pygame.quit()
                    exit()
                elif event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_1:
                        self.ai.difficulty = "easy"
                        running = False
                    elif event.key == pygame.K_2:
                        self.ai.difficulty = "medium"
                        running = False
                    elif event.key == pygame.K_3:
                        self.ai.difficulty = "hard"
                        running = False

    def select_piece(self, pos):
        row, col = pos
        if self.game.board[row][col] == 1:
            self.selected_piece = self.game.board[row][col]
            self.selected_pos = pos

    def move_piece(self, to_pos):
        if self.selected_pos:
            from_pos = self.selected_pos
            if self.game.move(from_pos, to_pos):
                self.selected_piece = None
                self.selected_pos = None
                self.turn = -1 

    def run(self):
        self.run_menu()
        running = True
        while running:
            self.screen.fill((0, 0, 0))
            self.draw_board()
            self.draw_pieces()

            if self.turn == -1:
                self.ai.choose_move()
                self.turn = 1 

            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    running = False
                elif event.type == pygame.MOUSEBUTTONDOWN and self.turn == 1:
                    x, y = event.pos
                    col = x // self.square_size
                    row = y // self.square_size
                    if self.selected_piece is None:
                        self.select_piece((row, col))
                    else:
                        self.move_piece((row, col))
            if not any(-1 in row for row in self.game.board):
                self.show_victory("Giocatore")
                running = False
            elif not any(1 in row for row in self.game.board):
                self.show_victory("AI")
                running = False

            pygame.display.flip()

        pygame.quit()
