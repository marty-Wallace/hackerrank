fn get_input_line() -> String {
    let mut buffer = String::new();
    std::io::stdin().read_line(&mut buffer).expect("Failed to read from stdin");
    buffer
}

fn all_nines(mut n : u32) -> bool {
    while n > 0 {
        if n % 10 != 9 {
            return false;
        }
        n = n/10;
    }
    return true;
}

fn is_beaut(s: &mut String) {
    if s.trim().len() <= 1 {
        println!("NO");
        return;
    }
    let max_length = s.len() / 2 + 1;
    

    for a in 1..max_length {
        let mut i = a;
        let mut last: u32 = s.trim()[0..i].parse().unwrap();
        let mut passed = true;
        
        let mut index = i;
        if all_nines(last) {
            i += 1;
        }
        while index+i < s.len() {   
            if s.chars().nth(index).unwrap() == '0'  && i > 1 {
                passed = false;
                break;
            }
            let current: u32 = s.trim()[index..index+i].parse().unwrap();
            
            if last+1 != current {
                passed = false;
                break;
            }

            last = current;
            if all_nines(last) {
                i += 1;
            }
            index += i;
        }
        
        passed = passed && index == s.trim().len();

        if passed {
            println!("YES {}", s[0..a].to_string());
            return;
        }
    }

    println!("NO");

}

fn main() {
    let line = get_input_line();
    let num: u32 = line.trim().parse().unwrap();

    for _ in 0..num {
        let mut line = get_input_line();
        is_beaut(&mut line);
    }


}
