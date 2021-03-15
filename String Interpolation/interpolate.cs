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
        var matches = re.Matches(rawText);

        if (matches.Count == 0){
            return rawText;
        }

        string output = re.Replace(rawText, match => dict[match.Groups[1].Value]);
        
        return output;
    }

}