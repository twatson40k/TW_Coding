# Create an empty board, represented by a list of 9 '-' characters:
emptyBoard = 9 * ['-']

# Set up a list of winning lines:
winningLines = [[0,1,2],[3,4,5],[6,7,8],[0,3,6],[1,4,7],[2,5,8],[0,4,8],[2,4,6]]


#############################################################################


def commonElements (list1, list2) :
    # Returns a list containing those elements that are in both list1 and list2
    # The common elements woill appear in the same order as they did in list1

    result = []            # So far we have found no common elements
    for elem in list1 :    # Look at each element in list1
        if elem in list2 :       # If the current element is also in list2
            result.append(elem)  #   add it to the end of the result list
    return result


#############################################################################


def whoseTurn (board) :
    # Returns the player whose turn it is to play, given the
    # current state of the board
    # Only works correctly if the board is not full

    if board.count('-') % 2 == 1 :  # If there's an odd number of empty squares,
        return 'X'                  #   it must be X's turn
    else :                          # otherwise
        return 'O'                  #   it's O's turn


#############################################################################


def isWinner (board, player) :
    # Returns True if board contains a winning line belonging to player
    # Otherwise returns False
    
    result = False      # Assume player hasn't won
    positions = []      # Assume no squares contain the player's symbol
    
    for i in range (9) :  # Look at each square (0..8) in turn
        if board[i] == player :  # If a square contains the player's symbol
            positions.append(i)  # add it to the list of squares that do

    for line in winningLines :  # work through the list of winning lines
        # one at a time, so that each can be compared with the list of
        # squares containing the player's symbol
        common = commonElements (line,positions)
        # common is a list containing those square numbers that are in both
        # the current winning line and the list of squares containing Xs
        count = len (common)
        if count == 3 :     # If this list contains 3 squares X has won
            result = True   # so the function should return True

    return result


#############################################################################


def boardAsString (board) :
    # Returns a string reptresentation of the board
    boardStr = ""

    for square in range (0,9) :
        boardStr = boardStr + " " + board [square] + " "
        if square % 3 == 2 :
            boardStr = boardStr + "\n"

    return boardStr


#############################################################################


def play() :
    # Run this sub-program to play the game
    
    theBoard = emptyBoard.copy()    # Start the game with an empty board

    print ("Welcome to the noughts and crosses game")
    print ("The board is empty:")
    display = boardAsString (theBoard)
    print (display)
    
    print ("Cross plays first")

    gameOver = False    # set to False because the game isn't over yet
    
    while not gameOver :

        sq = int(input("\nPlease choose a square number between 0 and 8 "))

        if theBoard[sq] == '-' :
        # this is an empty square, so we make the move
            player = whoseTurn (theBoard)  # find out which symbol to put in
            theBoard[sq] = player          # put the symbol in the list
            
            if isWinner (theBoard,player) :
            # the current player has won, so
                gameOver = True
                winner = player
            elif theBoard.count('-') == 0 :
            # otherwise when there are no empty spaces
                gameOver = True
                winner = '-'        # because nobody has won
            
            print ("Current state of the board is:")
            display = boardAsString (theBoard)
            print (display)
            
        else :
        # otherwise it is not an empty square, so we just show a message
            print ("That suqare is already full. Please choose another")

    # Once the game is over we need to print out the appropriate message:
    
    if winner == player :
    # the game stopped because the current player won, so
        print (player, "wins")
    else :
    # otherwise the game stopped because we ran out of empty squares, so
        print ("Game drawn")
    
    print ("\nThank you for playing Noughts and Crosses")


#############################################################################


play()

    
