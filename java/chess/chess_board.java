package chess;

public class chess_board {
    static int L = 8;
    static char board[][] = new char[L][L];
    public static void main(String[] args) {
        basic_board();
        pieces_setup();
        for(int i = 0; i<8; i++) {
            for(int j = 0; j<8; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println("");
        }
    }
    public static void basic_board() {
        char n1 = 'B';
        char n2 = 'W';
        char buf;


        for(int i = 0; i<8; i++) {
            for(int j = 0; j<8; j++) {
                buf = n1;
                n1= n2;
                n2= buf;
                board[i][j] = n1;
                
            }
            buf = n1;
            n1= n2;
            n2= buf;
        }
        
    }
    public static void pieces_setup() {
        char[] pieces = {'R','N','B', 'K'};
        char pawn = 'P';
        for(int j = 0; j< L/2; j++) {
            board[0][j] = pieces[j];
            board[0][L- j] = pieces[j];

            board[7][j] = pieces[j];
            board[7][L- j] = pieces[j];
        }
        board[0][3] = 'Q';
        board[7][3] = 'Q';
    }

}

