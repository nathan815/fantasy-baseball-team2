# COSC 381 Semester Project 1: Fantasy Baseball

Team 2 - Members: Ebrahim Qasem, Cassandra Flones, William Shields, Nathan Johnson

Google Drive Folder: https://drive.google.com/drive/u/1/folders/1pWbduhIalGVEvOMDqa0hteEXLKwPMFYn

## Project Submission Details

GitHub: https://github.com/emu-computer-science/fantasy-baseball-team2/

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

### Sample Output

```
...
```

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
