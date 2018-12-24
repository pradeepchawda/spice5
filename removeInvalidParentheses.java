class Solution {
    public List<String> removeInvalidParentheses(String s) {
        
    // go through the string char by char
        // remove parentheses from each place
            // check if its a valid string        
                // add it to result and return 
        
        // repeat till you get all valid paren or null
        HashSet<String> result = new HashSet<String>();
        HashSet<String> visited = new HashSet<String>();
        Deque<String> q = new ArrayDeque <String> ();
        
        // start with given string
        q.add(s);
        visited.add(s);
        while(!q.isEmpty()){
            result = new HashSet<String>();
            
            int size = q.size();
            
            for(int i=0; i<size;++i){
                String n = q.poll();           
                
                if(isValid(n)){// if valid
                    result.add(n);
                    continue;
                }
                // if not valid
                q.addAll(getAdj(n, visited));

            }
            
            if(!result.isEmpty()){
                return new ArrayList<>(result);
            }
            
        }
        return new ArrayList<>(result);
        
    }
        
        public  List<String> getAdj(String s, Set<String> visited){
        int i=-1;
        List<String> adjList = new ArrayList<>();
            
        for(char c : s.toCharArray()){            
            i++;
            if(c == '(' || c == ')'){
                String n = s.substring(0,i)+s.substring(i+1);
                if(!visited.contains(n)){
                    visited.add(n);
                    adjList.add(n);
                }
        }
        
        ///
            
        }
        
        return adjList;
        
    }


    public boolean isValid(String s){
        int score = 0;
         for(char c : s.toCharArray()){          
             if(c == '('){
                 score++;
             }
             else if(c == ')'){
                 score--;
             }
             if(score<0)
                return false;             
         }
        return score == 0;
    }
        
}
