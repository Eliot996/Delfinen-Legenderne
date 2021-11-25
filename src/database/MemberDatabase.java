package database;

import domain.member.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberDatabase {

    private ArrayList<Member> members = new ArrayList<>();

    public void addMember(Member member){
        members.add(member);
    }

    public void deleteMember(Member member){
        members.remove(member);
    }

    public Member getMember(String name){
        for (Member member : members) {
            if (member.getName().equals(name)){
                return member;
            }
        }
        return null;
    }

    public Member getMember(int memberNumber){
        for (Member member : members) {
            if (member.getMemberNumber() == memberNumber){
                return member;
            }
        }
        return null;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    public String toCSV() {
        StringBuilder sb = new StringBuilder();

        for (Member member :
                members) {
            sb.append(member.toCSV()).append('\n');
        }

        return sb.toString();
    }
}
