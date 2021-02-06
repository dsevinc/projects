/*** Based on "Build Your Own Text Editor" tutorial https://viewsourcecode.org/snaptoken/kilo/
 *  in progress
 ****/


/*** includes ***/

#include <ctype.h>
#include <errno.h>
#include <stdio.h> 
#include <stdlib.h>
#include <termios.h>
#include <unistd.h>

/*** defines ***/

#define CTRL_KEY(k) ((k) & 0x1f)

/*** data ***/

struct termios orig_termios;

/*** terminal ***/

void die(const char *s) {
    perror(s); //print error message
    exit(1);
}

void disableRawMode() {
    if (tcsetattr(STDIN_FILENO, TCSAFLUSH, &orig_termios) == -1) die("tcsetattr");
}

void enableRawMode() {
    if (tcgetattr(STDIN_FILENO, &orig_termios) == -1) die("tcgetattr");
    
    atexit(disableRawMode); //call disableRawMode automatically upon program exit

    struct termios raw = orig_termios; //copy original terminal attributes
    raw.c_iflag &= ~(BRKINT | ICRNL | INPCK | ISTRIP | IXON); //fix ctrl-m/ctrl-j, disable ctrl-s and ctrl-q
    raw.c_oflag &= ~(OPOST); //turn off output processing
    raw.c_cflag |= (CS8); // set character size to 8 bits per byte (bitmask with multiple bits)
    raw.c_lflag &= ~(ECHO | ICANON | IEXTEN | ISIG); //turn off echo, canonical mode, ctrl-c/ctrl-z, ctrl-o/ctrl-v
    raw.c_cc[VMIN] = 0; //min number of bytes needed before read() can occur
    raw.c_cc[VTIME] = 1; //max time to wait before read() returns
    if (tcsetattr(STDIN_FILENO, TCSAFLUSH, &raw) == -1) die("tcsetattr");
}

/*** init ***/

int main() {
    enableRawMode();
    while (1) {
        char c = '\0';
        if (read(STDIN_FILENO, &c, 1) == -1 && errno != EAGAIN) die("read");
        if (iscntrl(c)) { //test if c is a control character
            printf("%d\r\n", c);
        } else {
            printf("%d ('%c')\r\n",c,c);
        }
        if (c == CTRL_KEY('q')) break;
    }

    return 0;
}