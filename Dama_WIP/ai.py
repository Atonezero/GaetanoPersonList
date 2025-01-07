import random

class Ai:
    def __init__(self, dama, difficulty="medium"):
        self.dama = dama
        self.difficulty = difficulty

    def check_move(self, pos):
        x, y = pos
        moves = []     
        directions = [(1, -1), (1, 1)] 
        for dx, dy in directions:
            nx, ny = x + dx, y + dy
            if 0 <= nx < 8 and 0 <= ny < 8:
                # Normal move
                if self.dama.board[nx][ny] == 0:
                    moves.append(((x, y), (nx, ny)))
                # Capture move
                elif 0 <= nx + dx < 8 and 0 <= ny + dy < 8 and self.dama.board[nx][ny] == 1:
                    if self.dama.board[nx + dx][ny + dy] == 0:
                        moves.append(((x, y), (nx + dx, ny + dy)))
        return moves

    def choose_move(self):
        valid_moves = []
        for row in range(8):
            for col in range(8):
                if self.dama.board[row][col] == -1:
                    valid_moves.extend(self.check_move((row, col)))
        if not valid_moves:
            return

        if self.difficulty == "easy":
            move = random.choice(valid_moves)
        elif self.difficulty == "medium":
            capture_moves = [move for move in valid_moves if abs(move[1][0] - move[0][0]) == 2]
            move = random.choice(capture_moves) if capture_moves else random.choice(valid_moves)
        elif self.difficulty == "hard":
            _, move = self.minimax(valid_moves) 

        self.dama.move(move[0], move[1])

    def minimax(self, valid_moves, depth=3, maximizing=True):
        if depth == 0 or not valid_moves:
            return self.evaluate_board(), None 

        if maximizing:
            best_move = None
            best_value = -float('inf')
            for move in valid_moves:
                self.dama.move(move[0], move[1])
                next_moves = self.generate_possible_moves()
                value, _ = self.minimax(next_moves, depth-1, False)
                self.dama.undo_move(move)
                if value > best_value:
                    best_value = value
                    best_move = move
            return best_value, best_move
            best_move = None
            best_value = float('inf')
            for move in valid_moves:
                self.dama.move(move[0], move[1])
                next_moves = self.generate_possible_moves()
                value, _ = self.minimax(next_moves, depth-1, True)
                self.dama.undo_move(move)
                if value < best_value:
                    best_value = value
                    best_move = move
            return best_value, best_move

    def evaluate_board(self):
        score = 0
        for row in self.dama.board:
            for cell in row:
                if cell == -1:
                    score += 1
                elif cell == 1:
                    score -= 1 
        return score

    def generate_possible_moves(self):
        valid_moves = []
        for row in range(8):
            for col in range(8):
                if self.dama.board[row][col] == -1: 
                    valid_moves.extend(self.check_move((row, col)))
        return valid_moves
