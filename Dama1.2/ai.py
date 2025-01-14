import random

class Ai:
    def __init__(self, dama, difficulty="medium"):
        self.dama = dama
        self.difficulty = difficulty

    def check_move(self, pos):
        x, y = pos
        moves = []     
        # Consider both forward and backward moves for AI
        directions = [(1, -1), (1, 1), (-1, -1), (-1, 1)]
        
        for dx, dy in directions:
            nx, ny = x + dx, y + dy
            # Normal move
            if 0 <= nx < 8 and 0 <= ny < 8 and self.dama.board[nx][ny] == 0:
                moves.append(((x, y), (nx, ny)))
            
            # Capture move
            nx2, ny2 = x + 2*dx, y + 2*dy
            if (0 <= nx < 8 and 0 <= ny < 8 and 
                0 <= nx2 < 8 and 0 <= ny2 < 8):
                # Check if there's an opponent piece to capture
                if (self.dama.board[nx][ny] == 1 and  # Opponent's piece
                    self.dama.board[nx2][ny2] == 0):  # Empty destination
                    moves.append(((x, y), (nx2, ny2)))
        
        return moves


    def generate_possible_moves(self):
        valid_moves = []
        for row in range(8):
            for col in range(8):
                if self.dama.board[row][col] == -1:  # For AI's pieces
                    valid_moves.extend(self.check_move((row, col)))
        return valid_moves
    
    def minimax(self, depth, maximizing=True, alpha=-float('inf'), beta=float('inf')):
        # Check if game is over or depth limit reached
        if depth == 0:
            return self.evaluate_board(), None

        possible_moves = self.generate_possible_moves()
        
        # If no moves available, return current board evaluation
        if not possible_moves:
            return self.evaluate_board(), None

        if maximizing:
            max_eval = -float('inf')
            best_move = None
            
            for move in possible_moves:
                # Make a copy of the current board state
                original_board = [row.copy() for row in self.dama.board]
                
                # Apply the move
                self.dama.move(move[0], move[1])
                
                # Recursively evaluate
                eval_score, _ = self.minimax(depth - 1, False, alpha, beta)
                
                # Restore original board state
                self.dama.board = original_board
                
                # Update best move if needed
                if eval_score > max_eval:
                    max_eval = eval_score
                    best_move = move
                
                # Alpha-beta pruning
                alpha = max(alpha, eval_score)
                if beta <= alpha:
                    break
            
            return max_eval, best_move
        
        else:  # Minimizing player (human)
            min_eval = float('inf')
            best_move = None
            
            for move in possible_moves:
                # Make a copy of the current board state
                original_board = [row.copy() for row in self.dama.board]
                
                # Apply the move
                self.dama.move(move[0], move[1])
                
                # Recursively evaluate
                eval_score, _ = self.minimax(depth - 1, True, alpha, beta)
                
                # Restore original board state
                self.dama.board = original_board
                
                # Update best move if needed
                if eval_score < min_eval:
                    min_eval = eval_score
                    best_move = move
                
                # Alpha-beta pruning
                beta = min(beta, eval_score)
                if beta <= alpha:
                    break
            
            return min_eval, best_move

    def evaluate_board(self):
        # More sophisticated evaluation function
        score = 0
        for row in range(8):
            for col in range(8):
                piece = self.dama.board[row][col]
                if piece == -1:  # AI's pieces
                    # Bonus for pieces closer to opponent's side
                    score += 10 + row
                elif piece == 1:  # Player's pieces
                    # Penalty for player's pieces
                    score -= 10 + (7 - row)
        return score

    def choose_move(self):
        valid_moves = []
        for row in range(8):
            for col in range(8):
                if self.dama.board[row][col] == -1:  # AI's pieces
                    piece_moves = self.check_move((row, col))
                    valid_moves.extend(piece_moves)

        if not valid_moves:
            return

        if self.difficulty == "easy":
            move = random.choice(valid_moves)
        elif self.difficulty == "medium":
            # Prioritize capture moves
            capture_moves = [move for move in valid_moves if abs(move[1][0] - move[0][0]) == 2]
            move = random.choice(capture_moves) if capture_moves else random.choice(valid_moves)
        elif self.difficulty == "hard":
            # Improved minimax with depth and alpha-beta pruning
            depth = 4  # Increased depth for more complex decision making
            _, move = self.minimax(depth, True)

        if move:
            self.dama.move(move[0], move[1])
