# append.py
# short script to mod the 2letter state abbrv.
# into desired format
# Author: Ronny Macmaster
# Date : 2/22/15

try:
    fhand = open("states.txt")
    code = ""
    count = 0
    for line in fhand:
        state = line.rstrip()
        cmd = 'add("'+state+'"); '
        code += cmd
        if (count % 5) == 4:
            code += '\n'
        count += 1

    # store result
    fhand.close()
    fhand = open("code.txt", "w")
    fhand.write(code)
except:
    print "Could not open states.txt!"
finally:
    fhand.close()
