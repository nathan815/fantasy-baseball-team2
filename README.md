# COSC 381 Semester Project 1: Fantasy Baseball

Team 2 - Members: Ebrahim Qasem, Cassandra Flones, William Shields, Nathan Johnson

Google Drive Folder: https://drive.google.com/drive/u/1/folders/1pWbduhIalGVEvOMDqa0hteEXLKwPMFYn

## Dev Setup
1. [Install IntelliJ IDEA](https://www.jetbrains.com/idea/) if you don't yet have it. Students get Ultimate Edition for free, but Community Edition will also work for just Java development.
2. Clone our repo - `git clone https://github.com/emu-computer-science/fantasy-baseball-team2.git`
3. In IntelliJ IDEA: File > Open... and select `fantasy-baseball-team2` folder

### "Project SDK not defined"
If you see a "project SDK not defined" error bar, you need to configure JDK 13.0.1 in IntelliJ. To do this:

1. [Download JDK 13.0.1](https://www.oracle.com/technetwork/java/javase/downloads/jdk13-downloads-5672538.html) (.tar.gz file) and extract/uncompress it
2. Open Project Structure dialog (click folder with 3 dots in top right corner or press Shift key twice and search for it)
3. Click "New..." under Project SDK and select JDK
4. Select the JDK folder that you downloaded and uncompressed in step 1
5. Click OK

### JUnit4 errors
If you get errors regarding junit4 not being installed: open one of the tests, click on the red "org.junit. ..." text in the imports, then click the little floating exclamation icon near it. It will give the option to fix this issue by downloading the library. 

## Project Submission Details

GitHub: https://github.com/emu-computer-science/fantasy-baseball-team2/

Burndown Chart Sprint 2: https://docs.google.com/spreadsheets/d/1F4Bd0njqXnIszzWvBHnan9UiEYn3xPglXCtc-U_IlhE/edit#gid=1060632366

### Supported Stats
Pitchers
- ERA (EarnedRunAverage)
- SO (StrikeOuts)
- HA (HitsAllowed)
- RA (RunsAllowed)

Hitters
- AVG (Average)
- H (Hits)
- R (Runs)
- RBI (RunBattedIns)

### Sample Output Transcript

```
Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
IDRAFT "Anderson, T"

Successfully drafted player Tim Anderson (SS)

Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
ODRAFT "LeMahieu, D" B 

Successfully drafted player DJ LeMahieu (2B)

Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
ODRAFT "Newman, K" C

Successfully drafted player Kevin Newman (SS)

Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
ODRAFT "Moncada, Y" D

Successfully drafted player Yoan Moncada (3B)

Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
IDRAFT "Marte, K"

Successfully drafted player Ketel Marte (CF)

Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
ODRAFT "Gurriel, Y" B 

Successfully drafted player Yuli Gurriel (1B)

Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
ODRAFT "Brantley, M" C

Successfully drafted player Michael Brantley (LF)

Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
ODRAFT "McNeil, J" D

Successfully drafted player Jeff McNeil (LF)

Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
TEAM A

SS Anderson, Tim
CF Marte, Ketel

Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
TEAM B

1B Gurriel, Yuli
2B LeMahieu, DJ

Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
TEAM C

SS Newman, Kevin
LF Brantley, Michael

Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
TEAM D

3B Moncada, Yoan
LF McNeil, Jeff

Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
STARS D

3B Moncada, Yoan
LF McNeil, Jeff

Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
SAVE secondRound

The state of the system has been saved to secondRound.fantasy.txt

Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
QUIT

Do you want to save the current draft before quitting? (Y/N): Y
Enter file name: secondRound
The state of the system has been saved to secondRound.fantasy.txt
Program is terminated.
```

Follow up session:
```
Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
RESTORE secondRound

The state of the system has been restored from secondRound.fantasy.txt

Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
TEAM A

SS Anderson, Tim
CF Marte, Ketel

Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
TEAM B

1B Gurriel, Yuli
2B LeMahieu, DJ

Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
IDRAFT "Nobody"

Error: Player Nobody not found

Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
EVALFUN 1.05 * AVG + H + R


Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
PEVALFUN HA / RA + ERA


Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
OVERALL 1B

Name                          Team           Position       Valuation (1.05 * AVG + H + R)
Freeman, Freddie              ATL            1B             289.30975
Santana, Carlos               CLE            1B             271.29505
Abreu, Jose                   CWS            1B             265.2982
Alonso, Pete                  NYM            1B             258.273
Gurriel, Yuli                 HOU            1B             253.3129
Goldschmidt, Paul             STL            1B             252.273
Bell, Josh                    PIT            1B             240.29085
Rizzo, Anthony                CHC            1B             239.30765
Hosmer, Eric                  SD             1B             236.27825
Walker, Christian             ARI            1B             223.27195
Muncy, Max                    LAD            1B             223.26355
Votto, Joey                   CIN            1B             216.27405
Hoskins, Rhys                 PHI            1B             215.2373
Olson, Matt                   OAK            1B             202.28035
Belt, Brandon                 SF             1B             199.2457
Voit, Luke                    NYY            1B             185.27615
Cabrera, Miguel               DET            1B             180.2961
Pujols, Albert                LAA            1B             175.2562
Vogelbach, Daniel             SEA            1B             169.2184

Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
POVERALL

Name                          Team           Position       Valuation (HA / RA + ERA)
Porcello, Rick                BOS            P              7.256842105263157
Lopez, Reynaldo               CWS            P              7.0858823529411765
Junis, Jakob                  KC             P              7.017777777777778
Perez, Martin                 MIN            P              6.889230769230769
Nova, Ivan                    CWS            P              6.822803738317757
Quintana, Jose                CHC            P              6.59
Marquez, German               COL            P              6.5725
Bailey, Homer                 OAK            P              6.498571428571429
Roark, Tanner                 OAK            P              6.492857142857142
Lester, Jon                   CHC            P              6.48970297029703
Tanaka, Masahiro              NYY            P              6.407894736842105
Wainwright, Adam              STL            P              6.370722891566265
Kelly, Merrill                ARI            P              6.356842105263158
Boyd, Matthew                 DET            P              6.322376237623762
Mikolas, Miles                STL            P              6.304444444444444
Leake, Mike                   ARI            P              6.281228070175438
Syndergaard, Noah             NYM            P              6.200792079207921
Fried, Max                    ATL            P              6.194999999999999
Musgrove, Joe                 PIT            P              6.154285714285715
Anderson, Brett               OAK            P              6.1525
Keller, Brad                  KC             P              6.115
Eflin, Zach                   PHI            P              6.084545454545454
Wheeler, Zack                 NYM            P              6.06752688172043
Bauer, Trevor                 CIN            P              6.039322033898306
Lucchesi, Joey                SD             P              6.026153846153846
Rodriguez, Eduardo            BOS            P              6.02590909090909
Ray, Robbie                   ARI            P              5.988351648351648
Gonzales, Marco               SEA            P              5.971132075471698
Miley, Wade                   HOU            P              5.9559036144578315
Fiers, Mike                   OAK            P              5.9243902439024385
Lynn, Lance                   TEX            P              5.861011235955056
DeSclafani, Anthony           CIN            P              5.851038961038961
Sanchez, Anibal               WSH            P              5.837012987012987
Bumgarner, Madison            SF             P              5.829292929292929
Nola, Aaron                   PHI            P              5.804065934065934
Minor, Mike                   TEX            P              5.799302325581396
Alcantara, Sandy              MIA            P              5.7842553191489365
Berrios, Jose                 MIN            P              5.743829787234043
Darvish, Yu                   CHC            P              5.687317073170732
Teheran, Julio                ATL            P              5.63716049382716
Hendricks, Kyle               CHC            P              5.613846153846154
Stroman, Marcus               NYM            P              5.596623376623377
Samardzija, Jeff              SF             P              5.4687179487179485
Bieber, Shane                 CLE            P              5.442790697674418
Soroka, Mike                  ATL            P              5.412142857142857
Scherzer, Max                 WSH            P              5.3606779661016954
Strasburg, Stephen            WSH            P              5.357974683544304
Hudson, Dakota                STL            P              5.35
Ryu, Hyun-Jin                 LAD            P              5.3388679245283015
Corbin, Patrick               WSH            P              5.336419753086419
Kershaw, Clayton              LAD            P              5.331587301587302
Greinke, Zack                 HOU            P              5.327260273972603
Giolito, Lucas                CWS            P              5.308550724637682
Buehler, Walker               LAD            P              5.247012987012987
Castillo, Luis                CIN            P              5.228947368421053
Morton, Charlie               TB             P              5.219014084507043
deGrom, Jacob                 NYM            P              5.040169491525424
Gray, Sonny                   CIN            P              4.9377966101694915
Flaherty, Jack                STL            P              4.92741935483871
Verlander, Justin             HOU            P              4.655757575757576
Cole, Gerrit                  HOU            P              4.651515151515151

Menu:
-ODRAFT
-IDRAFT
-OVERALL
-POVERALL
-TEAM
-STARS
-SAVE
-QUIT
-RESTORE
-EVALFUN
-PEVALFUN

Enter your command: 
QUIT

Do you want to save the current draft before quitting? (Y/N): y
Enter file name: thirdRound
The state of the system has been saved to thirdRound.fantasy.txt
Program is terminated.
```
