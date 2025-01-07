class dama:
    def __init__(self):
        self.board = [[0] * 8 for _ in range(8)]
        self.init_scacchiera()

    def init_scacchiera(self):
        for riga in range(8):
            for colonna in range(8):
                if (riga + colonna) % 2 == 1:
                    if riga < 3:
                        self.board[riga][colonna] = -1 
                    elif riga > 4:
                        self.board[riga][colonna] = 1 

    def show_board(self):
        for riga in self.board:
            print(" ".join([str(x) for x in riga]))
        print()

    def undo_move(self, move):
        start, end = move
        x1, y1 = start
        x2, y2 = end

        self.board[x1][y1] = self.board[x2][y2]
        self.board[x2][y2] = 0

    def move(self, frm, to):
        x1, y1 = frm
        x2, y2 = to
        if self.board[x1][y1] == 0:
            print("Nessun pezzo da muovere")
            return False
        if self.board[x2][y2] != 0:
            print("La destinazione è occupata")
            return False
        
        if abs(x2 - x1) == 2 and abs(y2 - y1) == 2:
            captured_x = (x1 + x2) // 2
            captured_y = (y1 + y2) // 2
            if self.board[captured_x][captured_y] == 0 or self.board[captured_x][captured_y] == self.board[x1][y1]:
                print("Cattura non valida")
                return False
            self.board[captured_x][captured_y] = 0

        self.board[x2][y2] = self.board[x1][y1]
        self.board[x1][y1] = 0 
        return True

    def valid_moves(self, x, y):
        moves = []
        directions = [(-1, -1), (-1, 1)] if self.board[x][y] == 1 else [(1, -1), (1, 1)] 
        for dx, dy in directions:
            nx, ny = x + dx, y + dy
            if 0 <= nx < 8 and 0 <= ny < 8 and self.board[nx][ny] == 0:
                moves.append((nx, ny))
            nx, ny = x + 2 * dx, y + 2 * dy
            if 0 <= nx < 8 and 0 <= ny < 8 and self.board[nx][ny] == 0:
                if 0 <= x + dx < 8 and 0 <= y + dy < 8 and self.board[x + dx][y + dy] != 0 and self.board[x + dx][y + dy] != self.board[x][y]:
                    moves.append((nx, ny))
        return moves