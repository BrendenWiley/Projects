#main driver file. responsible for handling user input and displaying the current GameState object.

import ChessEngine
import pygame as p


WIDTH = HEIGHT = 512
DIMENSION = 8
SQ_SIZE = HEIGHT // DIMENSION
MAX_FPS = 15
IMAGES = {}

#initialize a global dictionary of images

def loadImages():
   
    pieces = ['wp', 'wR', 'wN', 'wB', 'wK', 'wQ', 'bB', 'bN', 'bQ', 'bK', 'bp', 'bR']
    for piece in pieces:
        IMAGES[piece] = p.transform.scale(p.image.load("C:\\Users\\brend\\Desktop\\Programs!\\Chess\\Chess\\Chess\\images\\" + piece + ".png"), (SQ_SIZE, SQ_SIZE))
    #access an image by saying 'IMAGES['wp']'


# handles user input and updating the graphics.

def main():
     p.init()
     screen = p.display.set_mode((WIDTH, HEIGHT))
     clock = p.time.Clock()
     screen.fill(p.Color("white"))
     gs = ChessEngine.GameState()
     loadImages() 
     running = True
     sqSelected = () #keeping track of the last click of the user
     playerClicks = [] #keep track of clicks
     while running: 
         for e in p.event.get():
             if e.type == p.QUIT:
                 running = False
             elif e.type == p.MOUSEBUTTONDOWN:
                 location = p.mouse.get_pos() #x,y location of mouse
                 col = location[0]//SQ_SIZE
                 row = location[1]//SQ_SIZE
                 if sqSelected == (row, col): #the user clicked the same square twice
                     sqSelected == () #clear
                     playerClicks = [] #clear
                 else:
                     sqSelected = (row, col)
                     playerClicks.append(sqSelected)
                 if len(playerClicks) == 2:
                    move = ChessEngine.Move(playerClicks[0], playerClicks[1], gs.board)
                    print(move.getChessNotation())
                    gs.makeMove(move)
                    sqSelected = () #reset user clicks
                    playerClicks = []
         drawGameState(screen, gs)
         clock.tick(MAX_FPS)
         p.display.flip()

#responsible for graphics
def drawGameState(screen, gs):
    drawBoard(screen) #draw squares on board
    drawPieces(screen, gs.board) #draw pieces on top of squares


def drawBoard(screen):
    colors = [p.Color("white"), p.Color("green")]
    for r in range(DIMENSION):
        for c in range(DIMENSION):
           color = colors[((r+c) % 2)]
           p.draw.rect(screen, color, p.Rect(c*SQ_SIZE, r*SQ_SIZE, SQ_SIZE, SQ_SIZE))

def drawPieces(screen, board):
    for r in range(DIMENSION):
        for c in range(DIMENSION):
            piece = board[r][c]
            if piece != "--":
                screen.blit(IMAGES[piece], p.Rect(c*SQ_SIZE, r*SQ_SIZE, SQ_SIZE, SQ_SIZE))

if __name__ == "__main__":
    main()
