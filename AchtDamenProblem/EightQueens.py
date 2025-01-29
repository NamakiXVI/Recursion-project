
#Libraries werden importiert
import customtkinter as ctk
from PIL import Image

#die Größe des Feldes wird festgelegt
boardSize = 8

board = [[False for i in range(0, boardSize)] for i in range(0, boardSize)]

targetQueens = boardSize

currentQueens = 0

primaryColor = "#418BCA"

secondaryColor = "#EAF4FE"

fieldSize = 80

screenSize = (fieldSize + 5) * boardSize + 100

root = ctk.CTk()
root.configure(width=screenSize, height=screenSize)

root.resizable(False, False)

uiFields = [[ctk.CTkLabel(master=root, width=fieldSize, height=fieldSize, text="", fg_color=primaryColor if i % 2 else "black", corner_radius=5) for i in range(0, boardSize)]
            for i in range(0, boardSize)]

queenImage = ctk.CTkImage(Image.open("queen.png"), size=(fieldSize - 10, fieldSize - 10))


def setUserInterface():
    currentColor = primaryColor

    for x in range(0, boardSize):
        for y in range(0, boardSize):

            uiFields[x][y].place(x= (fieldSize + 5) * x + 50, y=(fieldSize + 5) * y + 50)
            uiFields[x][y].configure(fg_color=currentColor)
            if currentColor == primaryColor:
                currentColor = secondaryColor
            else:
                currentColor = primaryColor
        if currentColor == primaryColor:
            currentColor = secondaryColor
        else:
            currentColor = primaryColor


def solveBoard(row):
    global currentQueens
    global targetQueens #Globale Variabeln werden mit global versehen um sie in der methode zu verwenden

    if currentQueens >= targetQueens: #wenn alle Queens platziert sind wird True zurückgegeben
        return True

    for col in range(0, boardSize):
        if isSafe(row, col):
            board[row][col] = True
            currentQueens += 1
            if solveBoard(row + 1): #Stack wird aufgebaut
                return True
            board[row][col] = False
            currentQueens -= 1

    return False


def isSafe(row, col):

    #Spalte Diagonale wird geprüft
    for i in range(row):
        if board[i][col]:
            return False

    #Linke Diagonale wird geprüft
    i, j = row, col
    while i >= 0 and j >= 0:
        if board[i][j]:
            return False
        i -= 1
        j -= 1

    #Rechte Diagonale wird geprüft
    i, j = row, col
    while i >= 0 and j < len(board):
        if board[i][j]:
            return False
        i -= 1
        j += 1

    #Zeile wird nicht geprüft, da nach jedem platzieren die Zeile gewechselt wird

    return True

#main Methode
def main():
    if solveBoard(0):
        print("Solved")

    else:
        print("No solution found")

#Methode zum updaten der UI
def updateUserInterface():
    for x in range(0, boardSize):
        for y in range(0, boardSize):
            if board[x][y]:
                uiFields[x][y].configure(image=queenImage)
            else:
                uiFields[x][y].configure(image=None)


# Alle werte in "board" werden auf False gesetzt
for x in range(0, boardSize):
    for y in range(0, boardSize):
        board[x][y] = False

main() #main Methode wird ausgeführts
setUserInterface()  #Methode zum aufsetzten der UI wird aufgerufen
updateUserInterface() #UI wird geupdated
root.mainloop() #Die Mainloop wird ausgeführt um das Programm offen zu halten bis es geschlossen wird
