using System;
using System.Text.RegularExpressions;
using System.Collections.Generic;

public static class AurorAlgorithm{

    public static void Main(){
        //Consile.WriteLine(Assert.Equal("Hello Jim", Interpolate("Hello [name]", new Dictionary<string, string>{{"name", "Jim"}})));
        //string output = Interpolate("testing <name> 12 testing", new Dictionary<string, string>{{"name", "Jim"}});
        string output2 = Interpolate("Hello [name] [[author]]", new Dictionary<string, string>{{"name", "Jim"}, {"author", "tim"}});
        Console.WriteLine(output2);
    }

    public static string Interpolate(string rawText, Dictionary<string, string> dict){
        Regex re = new Regex(@"(?<!\[)\[(\w+)\](?!\])");
        Regex re2 = new Regex(@"\[\[(\w+)\]\]");
        Dictionary<string, string> doubleBracketDict = new Dictionary<string, string>();
        
        var matches = re.Matches(rawText);
        if (matches.Count == 0){
            return rawText;
        }

        string halfOutput = re.Replace(rawText, match => dict[match.Groups[1].Value]);
        
        matches = re2.Matches(halfOutput);
        foreach (Match m in matches){
            int start = 1;
            string replacement = m.Value.Substring(start, (m.Value.Length-start-1));
            doubleBracketDict.Add(m.Value, replacement);
        }

        string output = re2.Replace(halfOutput, match => doubleBracketDict[match.Value]);
        
        return output;
    }

}