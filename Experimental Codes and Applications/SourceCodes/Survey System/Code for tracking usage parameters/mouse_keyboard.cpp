#define _WIN32_WINNT 0x0400
#pragma comment( lib, "user32.lib" )

#include <windows.h>
#include <stdio.h>
#include<iostream>
#include <fstream>
#include <ctime>
#include<chrono>

using namespace std;



HHOOK hKeyBoardHook = NULL;
HHOOK hMouseHook = NULL;
std::ofstream outfile;
clock_t startTime = clock();
clock_t startLeftClickTime=clock();//for doubleclick detection
short delta=0;//for mouse wheel
double cursorXDist=0.0;
double cursorYDist=0.0;


clock_t keyPressTime,keyReleaseTime,keyPressTimeOld,keyPressUpTimeUpToDown,keyDownToDownTime;
DWORD g_tcLastLeftButtonClickTime = 0; // Global declaration

//int keyPressCount = 0;


double duration,keyDownToUpTime;
string key="----";

auto start = std::chrono::high_resolution_clock::now();
auto finish = std::chrono::high_resolution_clock::now();
int clickTime=0;

int control,arrow,function,regular,backspace,enter,keyDownToUpCount,keyUpToDownCount,keyDownToDownCount;

static int numClicksLeft = 0;
     static int numClicksRight = 0;
     static int numClicksDouble = 0;
     static bool flag=false;



 LASTINPUTINFO liii;//for checking idle time

 DWORD    g_dwLastTick = 0;    // tick time of last input event
LONG    g_mouseLocX = -1;    // x-location of mouse position
LONG    g_mouseLocY = -1;    // y-location of mouse position



std::string getexepath()
{
  char result[ MAX_PATH ];
  return std::string( result, GetModuleFileName( NULL, result, MAX_PATH ) );
}


string deleteSubstr(string str,string toDelete)
{

    str.replace(str.find(toDelete), (int)toDelete.length(), "");
    return str;

}

void print_log()
{

         duration = ((clock()-startTime)/(double) CLOCKS_PER_SEC);
         if(duration>=20)
         {
             outfile<<"What are you Doing Boss???"<<endl;
         }
        if(duration>=5.0){



                time_t now = time(0);
                // Convert now to tm struct for local timezone
                tm* localtm = localtime(&now);

                outfile<<"Timestamp"<<endl<<asctime(localtm)<<" "<<clock()<<" start "<<startTime<<" end "<<CLOCKS_PER_SEC<<endl;
                outfile<<"leftClick "<<endl<<(double)numClicksLeft/duration<<endl;
                outfile<<"rightClick "<<endl<<(double)numClicksRight/duration<<endl;
                outfile<<"DoubleClick "<<endl<<(double)numClicksDouble/duration<<endl;
                outfile<<"MouseScroll "<<endl<<(double)delta/duration<<endl;
                outfile<<"CursorXDistance: "<<endl<<(double)cursorXDist/duration<<endl;
                outfile<<"CursorYDistance: "<<endl<<(double)cursorYDist/duration<<endl;

                if(keyPressUpTimeUpToDown>0)
                {
                    outfile<<"UpToDownTime: "<<endl<<(double)keyPressUpTimeUpToDown/duration<<endl;
                }
                if(keyDownToDownTime>0)
                {
                    outfile<<"DownToDownTime: "<<endl<<(double)keyDownToDownTime/duration<<endl;
                }
                outfile<<"DownToUpTime: "<<(double)keyDownToUpTime/duration<<endl;

                outfile<<"KeyBoard: "<<key<<endl;


//                outfile<<"keyPress "<<keyPressCount<<endl;

                outfile<<"Control-"<<(double)control/duration<<endl<<"Enter-"<<endl<<(double)enter/duration<<endl<<"Function-"<<endl<<(double)function/duration<<endl<<"BackSpace-"<<endl<<(double)backspace/duration<<endl<<"Arrow-"<<endl<<(double)arrow/duration<<endl<<"Regular-"<<(double)regular/duration<<endl;
               // outfile<<"Key Down to Up Time"<<endl<<(double)keyDownToUpTime<<endl<<clickTime<<"ns"<<endl;
                outfile<<"Mouse Clicks Per Sec"<<endl<<(double)((numClicksLeft+numClicksRight)/duration)<<endl<<endl;



                outfile<<"Duration"<<endl<<duration<<endl<<"Idle time"<<endl<<(GetTickCount()- g_dwLastTick)/1000<<endl<<endl<<endl;

                key="----";


                outfile.flush();
                numClicksLeft=0;
                numClicksRight=0;
                numClicksDouble=0;
                delta=0;
                startTime = clock();
                duration = 0;
                keyDownToUpTime=0;
                clickTime=0;
                cursorXDist=0.0;
                cursorYDist=0.0;

                control=0;
                backspace=0;
                function=0;
                arrow=0;
                enter=0;
                regular=0;

                }
}

/****************************************************************
  WH_KEYBOARD hook procedure
 ****************************************************************/

LRESULT CALLBACK KeyboardEvent(int nCode, WPARAM wParam, LPARAM lParam)
{
   // outfile<<"hii"<<endl;


    CHAR szBuf[128];
    HDC hdc;
    static int c = 0;
    size_t cch;
    HRESULT hResult;

    print_log();

    if (nCode < 0)  // do not process message
        return CallNextHookEx(hKeyBoardHook, nCode,
            wParam, lParam);
//keyboard hellooooooo
           if(wParam == WM_KEYDOWN || wParam == WM_SYSKEYDOWN)
    {
        start = std::chrono::high_resolution_clock::now();
        keyPressTime = clock();
        if(keyPressTimeOld!=0)
        {
            keyDownToDownTime+=keyPressTime-keyPressTimeOld;
            keyDownToDownCount++;
        }
        keyPressTimeOld = keyPressTime;

        if(keyReleaseTime!=0)
        {
                keyPressUpTimeUpToDown+= keyPressTime-keyReleaseTime;
                keyUpToDownCount++;
        }

        static bool capslock = false;
		static bool shift = false;
		char tmp[0xFF] = {0};
		std::string str;
		DWORD msg = 1;
		KBDLLHOOKSTRUCT st_hook = *((KBDLLHOOKSTRUCT*)lParam);
		bool printable;
		g_dwLastTick = GetTickCount();

		/*
		 * Get key name as string hi rayhan how are you????
		 */


        msg += (st_hook.scanCode << 16);
		msg += (st_hook.flags << 24);
		GetKeyNameText(msg, tmp, 0xFF);
		str = std::string(tmp);

		printable = (str.length() <= 1) ? true : false;

		/*
		 * Non-printable characters only:
		 * Some of these (namely; newline, space and tab) will be
		 * made into printable characters.
		 * Others are encapsulated in brackets ('[' and ']').
		 */

		if (!printable) {
			/*
			 * Keynames that change state are handled here.
			 */
			if (str == "CAPSLOCK")
				capslock = !capslock;
			else if (str == "SHIFT")
				shift = true;

			/*
			 * Keynames that may become printable characters are
			 * handled here.
			 */
			if(str=="Up" || str=="Down" || str=="Right" || str=="Left")arrow++;
			else if(str=="Backspace")backspace++;
			else if(str=="Enter")enter++;
			else if(str[0]=='F')function++;
			else control++;
			if (str == "ENTER") {

				str = "\n";
				printable = true;
			} else if (str == "SPACE") {
				str = " ";
				printable = true;
			} else if (str == "TAB") {
				str = "\t";
				printable = true;
			} else {
				str = ("[" + str + "]");
			}

		}

		/*
		 * Printable characters only:
		 * If shift is on and capslock is off or shift is off and
		 * capslock is on, make the character uppercase.
		 * If both are off or both are on, the character is lowercase
		 */
		if (printable) {
		    regular++;
			if (shift == capslock) { /* Lowercase */
				for (size_t i = 0; i < str.length(); ++i)
					str[i] = tolower(str[i]);
			} else { /* Uppercase */
				for (size_t i = 0; i < str.length(); ++i) {
					if (str[i] >= 'A' && str[i] <= 'Z') {
						str[i] = toupper(str[i]);
					}
				}
			}

			shift = false;
		}


        key+=str;
    }


          else if(wParam == WM_KEYUP || wParam == WM_SYSKEYUP)
    {
        finish = std::chrono::high_resolution_clock::now();


        clickTime = (std::chrono::duration_cast<std::chrono::nanoseconds>(finish-start).count()+clickTime)/2;

        keyReleaseTime = clock();
//        avgClickTime = (avgClickTime+(keyReleaseTime-keyPressTime)/(double) CLOCKS_PER_SEC)/2;

        keyDownToUpTime += (keyReleaseTime-keyPressTime);
        keyDownToUpCount++;

    }
   // outfile<<"hii"<<endl;
//enda
/*
    hdc = GetDC(gh_hwndMain);
    hResult = StringCchPrintf(szBuf, 128/sizeof(TCHAR), "KEYBOARD - nCode: %d, vk: %d, %d times ", nCode, wParam, c++);
    if (FAILED(hResult))
    {
    // TODO: write error handler
    }
    hResult = StringCchLength(szBuf, 128/sizeof(TCHAR), &cch);
    if (FAILED(hResult))
    {
    // TODO: write error handler
    }
    TextOut(hdc, 2, 115, szBuf, cch);
    ReleaseDC(gh_hwndMain, hdc);
*/
    return CallNextHookEx(hKeyBoardHook, nCode, wParam, lParam);
}



bool IsDoubleClick(MSLLHOOKSTRUCT ms)
            {
                  // perform check
                  //bool isDblClk = ((InTime(ms.time)) && (InBounds(ms.pt)));

                  return false;
            }
//MouseTracker

__declspec(dllexport) LRESULT CALLBACK MouseTracker(int nCode, WPARAM wParam, LPARAM lParam)
{
    MOUSEHOOKSTRUCT * pMouseStruct = (MOUSEHOOKSTRUCT *)lParam;


     if (pMouseStruct != NULL){

if(nCode == HC_ACTION){
if (pMouseStruct->pt.x != g_mouseLocX || pMouseStruct->pt.y != g_mouseLocY)
        {
            cursorXDist+=abs(pMouseStruct->pt.x-g_mouseLocX);
           cursorYDist+=abs(pMouseStruct->pt.y-g_mouseLocY);

            g_mouseLocX = pMouseStruct->pt.x;
            g_mouseLocY = pMouseStruct->pt.y;
            g_dwLastTick = GetTickCount();
        }
}
        if(wParam == WM_LBUTTONDOWN)//for singleclick
        {

             //MOUSEHOOKSTRUCT* pStruct = (MOUSEHOOKSTRUCT*)lParam;
        //we will assume that any mouse msg with
        //the same locations as spurious

           /* if(1000*((clock()-startLeftClickTime)/(double) CLOCKS_PER_SEC)>=GetDoubleClickTime())
            {
                flag=false;
            }
             if(flag && 1000*((clock()-startLeftClickTime)/(double) CLOCKS_PER_SEC)<GetDoubleClickTime())
        {
            numClicksDouble++;
            flag=false;
        }

        else{
            numClicksLeft++;
            flag=true;
            startLeftClickTime=clock();

        }*/

if(GetTickCount() < g_tcLastLeftButtonClickTime + GetDoubleClickTime())//for doubleclick
            {
                // Event occured on desktop
               /* if(WindowFromPoint(((MSLLHOOKSTRUCT *)lParam)->pt) == g_hFolderView)
                {
                    // Do something here
                }*/
                numClicksDouble++;
                numClicksLeft--;
            }
            // Save timestamp of this mouse click (maybe another one will occur)
            else
                {
                    g_tcLastLeftButtonClickTime = GetTickCount();
                    numClicksLeft++;
                }
            //mouseX = pMouseStruct->pt.x;
            //mouseY = pMouseStruct->pt.y;

            //cout << mouseX;
            //cout << mouseY;
            //printf("MouseClicks: %d\n",numClicks);
        }
        if(wParam == WM_RBUTTONDOWN)
        {
            numClicksRight++;
        }

        if(wParam == WM_MOUSEWHEEL)
        {
            delta+=5;
            //delta+= (short)GET_WHEEL_DELTA_WPARAM(lParam);
           // MSLLHOOKSTRUCT *pMhs = (MSLLHOOKSTRUCT *)lParam;
           // delta += HIWORD(pMhs->mouseData);
            //delta+= HIWORD(wParam)
        }


//keyboard

          /* if(wParam == WM_KEYDOWN || wParam == WM_SYSKEYDOWN)
    {
        outfile<<"keyboard works !!! hurreeeehhh !!!!"<<endl;
    }*/

//end


     }




print_log();

    //if (pMouseStruct != NULL)
      //  printf("Mouse position X = %d  Mouse Position Y = %d\n", pMouseStruct->pt.x,pMouseStruct->pt.y);
    return CallNextHookEx(hMouseHook,
        nCode,wParam,lParam);
}


//mousetracker ends...

void MessageLoop()
{
    MSG message;
    while (GetMessage(&message,NULL,0,0)) {

        TranslateMessage( &message );
        DispatchMessage( &message );
    }
}

DWORD WINAPI MyKeyLogger(LPVOID lpParm)
{
    HINSTANCE hInstance = GetModuleHandle(NULL);
    if (!hInstance) hInstance = LoadLibrary((LPCSTR) lpParm);
    if (!hInstance) return 1;

 g_dwLastTick = GetTickCount(); // init count

    hKeyBoardHook = SetWindowsHookEx (
        WH_KEYBOARD_LL,
        (HOOKPROC) KeyboardEvent,
        hInstance,
        NULL
        );
    hMouseHook = SetWindowsHookEx(WH_MOUSE_LL,
                                     (HOOKPROC) MouseTracker,hInstance,NULL);



    MessageLoop();


    //UnhookWindowsHookEx(hMouseHook);
    UnhookWindowsHookEx(hKeyBoardHook);

    return 0;
}

BOOL IsMouseWheelPresent()
{
    return (GetSystemMetrics(SM_MOUSEWHEELPRESENT) != 0);
}
int main(int argc, char** argv)
{
WNDCLASS wc = { };
    wc.style = CS_DBLCLKS;

    /* Set other structure members. */

    RegisterClass(&wc);
    string x = getexepath();
    string y;
    //cout<<x<<endl;
    //y=argv[2];

    x = deleteSubstr(x,"mouse_keyboard.exe");
    if(argc>2)
    {
        y=argv[2];
        x+=("data/"+y+"/");

    }
    x+="test.txt";
    cout<<x<<endl;
  outfile.open(x.c_str(), std::ios_base::app);
    //freopen ("mouse_click.txt","w",stdout);
    HANDLE hThread;
    DWORD dwThread;

    HANDLE kThread;
    DWORD wThread;

    outfile<<argv[1]<<endl;    //uncomment it before using in the survey




    if(IsMouseWheelPresent())
    {
        outfile<<"mouse wheel present...";
    }
    else
    {
        outfile<<"mouse wheel not present...";
    }
    outfile<<endl;



    hThread = CreateThread(NULL,NULL,(LPTHREAD_START_ROUTINE)
        MyKeyLogger, (LPVOID) argv[0], NULL, &dwThread);


    if (hThread)
        return WaitForSingleObject(hThread,INFINITE);
    else return 1;

}
