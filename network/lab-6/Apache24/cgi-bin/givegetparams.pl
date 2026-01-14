#!C:\users\Anfourm\perl\bin\perl.exe

use strict;
use warnings;
use CGI qw(:standard);

print header(-type => 'text/html; charset=UTF-8');
print start_html('GET Parameters');

my $q = CGI->new;

print "<h2>GET Parameters</h2>";
print "<table border='1' cellpadding='5'>\n";
print "<tr><th>Parameter</th><th>Value</th></tr>\n";

foreach my $param ($q->param) {
    my $value = join(", ", $q->param($param));
    print "<tr><td>" . escapeHTML($param) . "</td><td>" . escapeHTML($value) . "</td></tr>\n";
}

print "</table>\n";
print end_html;