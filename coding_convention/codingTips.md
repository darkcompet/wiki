# Coding Experience Tips

> @infor: info@kilobytes.com.vn  
> @author: hoa.nb@kilobytes.com.vn  
> @author: co.vp@kilobytes.com.vn

## Optimization vs Readability

Always write code that simple to read and which will be understandable for developers. Because time
and resources that will be spent on hard readable code will be much higher than what you get from
optimization.

If you need to make optimization, then make it like independent module with DI, with 100% test coverage
and which will not be touched for at least one year.

## Architecture first

I saw many people who was saying “We need doing things fast we have no time on making architecture”.
And about 99% of them got big problems because of such thinking.

Writing code without thinking of its architecture is useless in the same way as dreaming about your
desires without a plan of achieving them.

Before writing the first line of the code, you should understand what it will be doing, how, what it will
use, how modules, services will work with each other, what structure will it have, how it will be tested
and debugged, and how it will be updated.

## Test Coverage

Tests are good thing but they are not always affordable and make sense for the project.

When you need tests:
- When you are writing modules, micro-services which will be not touched for at least one month.
- When you are writing open source code.
- When you are writing code that touches financial channels.
- When you have resources for updating tests at the same time as code was updated.

When you don’t need test:
- When you are a startup.
- When you have small team and code changing is fast.
- When you write scripts that can be simply tested manually by their output.

Remember that code with badly written tests can be more harmful then code without tests.

## Keep It Simple

Don’t write complex code. More it simpler then less bugs it may have and less time needed to debug them.
Code should do only what it need without tons of abstraction and other OOP shit (especially it concerns
Java developers) + 20% of things that may be needed in future to update it in simple way.

## Comments on Global (Package, Class, Function)

Comments showing bad code. Good code should be understandable without a line of comments. But what to do
to save time for new developers? — Write simple inline documentation describing what and how method work.

This will save much time for understanding and even more — it will give people more chances to come up
with better implementation of this method. And also it will be good start for global code documentation.

## Hard coupled vs Less Coupled

Always try to use micro-service architecture. Monolithic software can run faster than micro-service
software, but only in the context of one server.
Micro-services give you possibility to distribute your soft efficiently not only on many servers but
sometimes even on one machine(i mean process distribution).

## Code Reviews

Code review can be as good as it can be bad.

You can organize code review only if you have developer who understand 95% of the code and who can
monitor all updates without wasting to much time. In other situation it will be just time consuming
and everyone will hate this.

On this part got to many questions so describe this more deeply.
Many people think that code review it’s a good way of teaching new guys, or teammates who works on
different part of code. But the main target of code review it’s maintaining code quality, and not
teaching. Let’s imagine that your team making code for controlling cooling system for nuclear reactor,
or space rocket engine. And you made huge mistake in very hard logic, and then you are giving this for
code review to the new guy. How to you think what would be the risk of accident? — On my practice more
than 70%.

Good team is where each person has own role and responsible for exact piece of work. If someone want to
understand another piece of code then he goes to a person responsible for it ant ask her. Impossible to
know everything and better excellent understand small piece of code than all but on 30%.

## Refactoring does not work

During my career i heard many times “Don’t worry we will refactor it in future”. And in future this
results in big technology debt or in deleting all the code and writing from scratch.

So don’t get a debt unless you have money to develop you software few times from scratch.

## Don’t write code when you are tired or in a bad mood

When developers tired they are making `x2~5` more bugs and mistakes then when they are full of energy.
So working more is very bad practice. That’s why more and more countries thinking about 6 hours work
day, and some of them already have it. Mental work is not the same as working with your biceps .

## Don’t write all at once — make developing iterative

Before writing code analyze and predict, what your customers/clients really need, then select MVF (Most
Valuable Features) that you can develop with good quality in a short term. Use such iterations to deploy
quality updates, and not waist time and resources on unreasonable desires and sacrifice with quality.

## Automation vs Manual

Automation is a 100% success in a long term. So if you have resources to automate something right now it
should be done. You may think “it takes only 5 minute, why i should automate this?”. But lets calculate
this. For example it’s an everyday task for team of 5 developers. It takes `5 mins * 5 devs * 21 days *
12 month = 6 300 mins = 105 hours = 13.125 days ~ 5250 $`, and how much this will cost if you have
40,000 employees?

## Go out, get hobbies

Work differentiation increases mental abilities and gives new fresh ideas. So make pauses and go out
on fresh air, talk with friends, play the guitar, etc.

## Learn new things as you get free time

When people stop learning they start to degrade.
