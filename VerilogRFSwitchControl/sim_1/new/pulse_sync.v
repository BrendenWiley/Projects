//cpi
module syncdelay (output switch, input pmt, input sysclk,
input [0:0] btn);
//variables
reg [1:0] ja;

assign switch = ja[1];
reg [11:0] count = 0;
reg [11:0] cpi = 0;
reg pmt_detected,
high = 1,
low;
//Constants
parameter PRTWIDTH = 2400; //200us time in clock cycles (take pulse time in us and multiply by 12)
parameter DELAY = 9; //500ns delay (take delay in ns and multiply by .018)
parameter PRTDELAY = 0; //50us time in clock cycles0
parameter CPILENGTH = 99;

always @(posedge sysclk) begin

if(pmt == 1'b1) begin
pmt_detected <= 1;
end

if(pmt_detected == 1) begin
count <= count + 1;
end
if (btn[0]) begin;
pmt_detected <= 0;
count <= 0;
cpi <= 0;
end
//Generate Normal Pulses
if(cpi != CPILENGTH && count == PRTWIDTH - DELAY) begin
if(high == 1) begin
ja[1] <= 1'b1;
high <= 0;
low <= 1;
pmt_detected <= 0;
count <= 0;
cpi <= cpi + 1;
end
else if(low == 1) begin
ja[1] <= 1'b0;
high <= 1;
low <= 0;
pmt_detected <= 0;
count <= 0;
cpi <= cpi + 1;
end
end
//Generate CPI pulse
else if (cpi == CPILENGTH && count == PRTWIDTH - DELAY + PRTDELAY) begin
ja[1] <= 1'b0;
high <= 1;
low <= 0;
cpi <= 0;
pmt_detected <= 0;
count <= 0;
end
end
endmodule
