function validCoords () {
        if [ $# -ne 2 ]
        then
                echo 0
        elif [ -z "$1" -o -z "$2" ]
        then
                echo 0
        elif [ $1 -ge 0 -a $1 -le 2 -a $2 -ge 0 -a $2 -le 2 ]
        then
                echo 1
        else
                echo 0
        fi
        
}

function start {
	echo "Enter player1\`s name:"                                             
	read player1                                                    
	echo "Enter player2\`s name:"                                   
	read player2                                                              
	echo "$player1, choose a symbol 'O' or 'X':"                              
	read symbol1                                                              
	if [ $symbol1 == "O" ]                                                    
	then                                                                      
		symbol2="X"
	else                                                                  
		symbol2="O"	
	fi
	touch "$player1-$player2-timestamp.log"                                                                
	echo "$player1 plays with $symbol1 and $player2 plays with $symbol2"
echo "
| | | |
-------
| | | |
-------
| | | |
" | tee duska.txt
	playGame $symbol1 $symbol2
}

function playGame {
	secondWinner=0
	firstWinner=0

	while [ "$(gameEnd)" == "0" ]
	do
		askPlayer1 $symbol1
		if [ "$(gameEnd)" == "1" ]
		then
			firstWinner=1
		elif [ "$(isGameEven)" == "1" ]
		then
			break
			exit 0
		else
			askPlayer2 $symbol2
			if [ "$(gameEnd)" == "1" ]
			then
				secondWinner=1
			elif [ "$(isGameEven)" == "1" ]
			then
				break
				exit 0
			fi
		fi
		
		while read line
		do
		  echo $line
		done < "$player1-$player2-timestamp.log"
	done
	if [ $firstWinner -eq 1 ]
	then
		echo "$player1 wins!"
	elif [ $secondWinner -eq 1 ]
	then
		echo "$player2 wins!"
	else
		echo "Game is even!"
	fi
}

function askPlayer1 {
	echo "$player1, choose a field (row, col):"                       
	read hod1                                                         
	row1=${hod1:1:1}
	col1=${hod1:4:1}
	while [ "$(validCoords $row1 $col1)" == "0" ]
	do	
		echo "Wrong coordinates"
		echo "$player1, choose a field (row, col):"  
		read hod1                                                 
		row1=${hod1:1:1}
		col1=${hod1:4:1}
	done
	echo "$player1-$hod1" >> "$player1-$player2-timestamp.log"
	
	appendToTable $row1 $col1 $1
}

function askPlayer2 {
	echo "$player2, choose a field (row, col):"
	read hod2
	row2=${hod2:1:1}
	col2=${hod2:4:1}
	while [ "$(validCoords $row2 $col2)" == "0" ]
	do
		echo "Wrong coordinates"
		echo "$player2, choose a field (row, col):"  
		read hod2
		row2=${hod2:1:1}
		col2=${hod2:4:1}
	done
	echo "$player2-$hod2" >> "$player1-$player2-timestamp.log"
	appendToTable $row2 $col2 $1

}

function appendToTable () {
	i=0
	line0=""
	line1=""
	line2=""
	line3=""
	line4=""
	line5=""
	line6=""
	while read line
    do
		if [ $i -eq 0 ]
		then
			line0=$line
		elif [ $i -eq 1 ]
		then
			line1=$line
		elif [ $i -eq 2 ]
		then
			line2=$line
		elif [ $i -eq 3 ]
		then
			line3=$line
		elif [ $i -eq 4 ]
		then
			line4=$line
		elif [ $i -eq 5 ]
		then
			line5=$line
		elif [ $i -eq 6 ]
		then
			line6=$line
		fi
		i=`expr $i + 1`
    done < duska.txt
	x=$1
	y=$2
	znak=$3
	
	if [ $x -eq 0 ]
	then
		if [ $y -eq 0 ]
		then
			line1=$(echo $line1 | sed s/' '/$znak/1)
		elif [ $y -eq 1 ]
		then
			first1=$(echo "$line1" | cut -d'|' -f2)
			if [ "$first1" != " " ]
			then
				line1=$(echo $line1 | sed s/' '/$znak/1)
			else
				line1=$(echo $line1 | sed s/' '/$znak/2)
			fi
		elif [ $y -eq 2 ]
		then
			znak1=$(echo "$line1" | cut -d'|' -f2)
			znak2=$(echo "$line1" | cut -d'|' -f3)
			if [ "$znak1" != " " -a "$znak2" != " " ]
			then
				line1=$(echo $line1 | sed s/' '/$znak/1)
			elif [ "$znak1" != " " -o "$znak2" != " " ]
			then
				line1=$(echo $line1 | sed s/' '/$znak/2)
			else
				line1=$(echo $line1 | sed s/' '/$znak/3)
			fi
			
		fi
	elif [ $x -eq 1 ]
	then
		if [ $y -eq 0 ]
		then
			line3=$(echo $line3 | sed s/' '/$znak/1)
		elif [ $y -eq 1 ]
		then
			first2=$(echo "$line3" | cut -d'|' -f2)
			if [ "$first2" != " " ]
			then
				line3=$(echo $line3 | sed s/' '/$znak/1)
			else
				line3=$(echo $line3 | sed s/' '/$znak/2)
			fi
		elif [ $y -eq 2 ]
		then
			znak3=$(echo "$line3" | cut -d'|' -f2)
			znak4=$(echo "$line3" | cut -d'|' -f3)
			if [ "$znak3" != " " -a "$znak4" != " " ]
			then
				line3=$(echo $line3 | sed s/' '/$znak/1)
			elif [ "$znak3" != " " -o "$znak4" != " " ]
			then
				line3=$(echo $line3 | sed s/' '/$znak/2)
			else
				line3=$(echo $line3 | sed s/' '/$znak/3)
			fi
		fi
	elif [ $x -eq 2 ]
	then
		if [ $y -eq 0 ]
		then
			line5=$(echo $line5 | sed s/' '/$znak/1)
		elif [ $y -eq 1 ]
		then
			first3=$(echo "$line5" | cut -d'|' -f2)
			if [ "$first3" != " " ]
			then
				line5=$(echo $line5 | sed s/' '/$znak/1)
			else
				line5=$(echo $line5 | sed s/' '/$znak/2)
			fi
		elif [ $y -eq 2 ]
		then
			znak5=$(echo "$line5" | cut -d'|' -f2)
			znak6=$(echo "$line5" | cut -d'|' -f3)
			if [ "$znak5" != " " -a "$znak6" != " " ]
			then
				line5=$(echo $line5 | sed s/' '/$znak/1)
			elif [ "$znak5" != " " -o "$znak6" != " " ]
			then
				line5=$(echo $line5 | sed s/' '/$znak/2)
			else
				line5=$(echo $line5 | sed s/' '/$znak/3)
			fi
		fi
	fi
	
echo "$line0
$line1
$line2
$line3
$line4
$line5
$line6" | tee duska.txt
		
}

function gameEnd {
	i=0
	line0=""
	line1=""
	line2=""
	line3=""
	line4=""
	line5=""
	line6=""
	while read line
    do
		if [ $i -eq 0 ]
		then
			line0=$line
		elif [ $i -eq 1 ]
		then
			line1=$line
		elif [ $i -eq 2 ]
		then
			line2=$line
		elif [ $i -eq 3 ]
		then
			line3=$line
		elif [ $i -eq 4 ]
		then
			line4=$line
		elif [ $i -eq 5 ]
		then
			line5=$line
		elif [ $i -eq 6 ]
		then
			line6=$line
		fi
		i=`expr $i + 1`
    done < "duska.txt"
	
	sym1=$(echo "$line1" | cut -d'|' -f2)
	sym2=$(echo "$line1" | cut -d'|' -f3)
	sym3=$(echo "$line1" | cut -d'|' -f4)
	sym4=$(echo "$line3" | cut -d'|' -f2)
	sym5=$(echo "$line3" | cut -d'|' -f3)
	sym6=$(echo "$line3" | cut -d'|' -f4)
	sym7=$(echo "$line5" | cut -d'|' -f2)
	sym8=$(echo "$line5" | cut -d'|' -f3)
	sym9=$(echo "$line5" | cut -d'|' -f4)
	
	if [ "$sym1" == "$sym2" -a "$sym2" == "$sym3" -a "$sym1" != " " ]
	then
		echo 1
	elif [ "$sym4" == "$sym5" -a "$sym5" == "$sym6" -a "$sym4" != " " ]
	then
		echo 1
	elif [ "$sym7" == "$sym8" -a "$sym8" == "$sym9" -a "$sym7" != " " ]
	then
		echo 1
	elif [ "$sym1" == "$sym4" -a "$sym4" == "$sym7" -a "$sym1" != " " ]
	then
		echo 1	
	elif [ "$sym2" == "$sym5" -a "$sym5" == "$sym8" -a "$sym2" != " " ]
	then
		echo 1	
	elif [ "$sym3" == "$sym6" -a "$sym6" == "$sym9" -a "$sym3" != " " ]
	then
		echo 1	
	elif [ "$sym1" == "$sym5" -a "$sym5" == "$sym9" -a "$sym1" != " " ]
	then
		echo 1
	elif [ "$sym3" == "$sym5" -a "$sym5" == "$sym7" -a "$sym3" != " " ]
	then
		echo 1
	else
		echo 0
	fi
}

function isGameEven {
		i=0
	line0=""
	line1=""
	line2=""
	line3=""
	line4=""
	line5=""
	line6=""
	while read line
    do
		if [ $i -eq 0 ]
		then
			line0=$line
		elif [ $i -eq 1 ]
		then
			line1=$line
		elif [ $i -eq 2 ]
		then
			line2=$line
		elif [ $i -eq 3 ]
		then
			line3=$line
		elif [ $i -eq 4 ]
		then
			line4=$line
		elif [ $i -eq 5 ]
		then
			line5=$line
		elif [ $i -eq 6 ]
		then
			line6=$line
		fi
		i=`expr $i + 1`
    done < "duska.txt"
	
	sym1=$(echo "$line1" | cut -d'|' -f2)
	sym2=$(echo "$line1" | cut -d'|' -f3)
	sym3=$(echo "$line1" | cut -d'|' -f4)
	sym4=$(echo "$line3" | cut -d'|' -f2)
	sym5=$(echo "$line3" | cut -d'|' -f3)
	sym6=$(echo "$line3" | cut -d'|' -f4)
	sym7=$(echo "$line5" | cut -d'|' -f2)
	sym8=$(echo "$line5" | cut -d'|' -f3)
	sym9=$(echo "$line5" | cut -d'|' -f4)
	
	if [ "$sym1" != " " -a "$sym2" != " " -a "$sym3" != " " -a "$sym4" != " " -a "$sym5" != " " -a "$sym6" != " " -a "$sym7" != " " -a "$sym8" != " " -a "$sym9" != " " ]
	then
		echo 1
	else 
		echo 0
	fi
}

start