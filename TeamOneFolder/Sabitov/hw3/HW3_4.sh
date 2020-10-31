#/!bin/bash

options=(
	'1'
	"2"
	'3'
	'4'
	'5'
)
echo 'Please choose an option'
select input in "${options[@]}"; do
	case "$input" in
		"${options[0]}")
			ls
			;;
		"${options[1]}")
			date $'+Time: %T\nDate: %D'
			;;
		"${options[2]}")
			read -p 'What file do you wish to check? ' finput
			if [[ -d $finput ]]; then
				format='%s is a directory.\n'
			elif [[ -f $finput ]]; then
				format='%s is a file.\n'
			else
				format='%s does not exist.\n'
			fi
			printf "$format" "$finput"
			;;
		"${options[3]}")
			read -p 'Please enter filename: ' binput
			cp "$binput"{,.bak}
			;;
		"${options[4]}")
			break
			;;
		*)
			echo 'Not a valid option.'
			;;
	esac
done