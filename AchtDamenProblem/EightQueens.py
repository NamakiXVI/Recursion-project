
import customtkinter as ctk
from PIL import Image, ImageTk

boardSize = 8

delay = 10

board = [[False for i in range(0, boardSize)] for i in range(0, boardSize)]

targetQueens = boardSize

currentQueens = 0

fieldSize = 70

root = ctk.CTk()
root.configure(width=800, height=800)

uiFields = [[ctk.CTkLabel(master=root, width=fieldSize, height=fieldSize, text="", fg_color="white" if i % 2 else "black", corner_radius=5) for i in range(0, boardSize)]
            for i in range(0, boardSize)]

queenImage = ctk.CTkImage(Image.open("queen.png"), size=(fieldSize - 10, fieldSize - 10))


def setUserInterface():
    print("userInterface")
    currentColor = "white"

    for x in range(0, boardSize):
        for y in range(0, boardSize):

            uiFields[x][y].place(x=(fieldSize + 5) * x + 50, y=(fieldSize + 5) * y + 50)
            uiFields[x][y].configure(fg_color=currentColor)
            if currentColor == "white":
                currentColor = "brown"
            else:
                currentColor = "white"
        if currentColor == "white":
            currentColor = "brown"
        else:
            currentColor = "white"

    updateUserInterface()
    root.mainloop()


def solveBoard(row):
    global currentQueens
    global targetQueens

    if currentQueens >= targetQueens:
        print(board)
        return True

    for col in range(0, boardSize):
        if isSafe(row, col):
            board[row][col] = True
            currentQueens += 1
            if solveBoard(row + 1):
                return True
            board[row][col] = False
            currentQueens -= 1

    return False


def isSafe(row, col):
    # Überprüfung der Spalte
    for i in range(row):
        if board[i][col]:
            return False

    # Überprüfung der linken oberen Diagonale
    i, j = row, col
    while i >= 0 and j >= 0:
        if board[i][j]:
            return False
        i -= 1
        j -= 1

    # Überprüfung der rechten oberen Diagonale
    i, j = row, col
    while i >= 0 and j < len(board):
        if board[i][j]:
            return False
        i -= 1
        j += 1

    return True


def main():
    if solveBoard(0):
        print("Solved")

    else:
        print("No solution found")


def updateUserInterface():
    for x in range(0, boardSize):
        for y in range(0, boardSize):
            if board[x][y]:
                uiFields[x][y].configure(image=queenImage)
            else:
                uiFields[x][y].configure(image=None)
    print("Test")


for x in range(0, boardSize):
    for y in range(0, boardSize):
        board[x][y] = False

main()
setUserInterface()
updateUserInterface()
